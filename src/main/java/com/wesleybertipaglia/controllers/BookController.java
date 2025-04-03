package com.wesleybertipaglia.controllers;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.wesleybertipaglia.dtos.Book;

public class BookController {
    public final HttpController httpController;

    public final String api_url;

    public BookController() {
        this.httpController = new HttpController();
        this.api_url = "https://www.googleapis.com/books/v1";
    }

    public List<Book> getBook(String title) {
        title = URLEncoder.encode(title, StandardCharsets.UTF_8);
        String url = api_url + "/volumes?q=" + title;

        try {
            var response = httpController.get(url).body();

            if (response == null) {
                throw new Exception("Response is null");
            }

            JSONObject jsonResponse = new JSONObject(response);
            JSONArray items = jsonResponse.optJSONArray("items");

            if (items == null) {
                return new ArrayList<>();
            }

            List<Book> books = new ArrayList<>();
            for (int i = 0; i < items.length(); i++) {
                JSONObject itemJson = items.getJSONObject(i);
                JSONObject volumeInfo = itemJson.getJSONObject("volumeInfo");

                String id = itemJson.optString("id", "N/A");
                String link = itemJson.optString("selfLink", "N/A");
                String bookTitle = volumeInfo.optString("title", "No Title");
                String subtitle = volumeInfo.optString("subtitle", "");
                String publisher = volumeInfo.optString("publisher", "Unknown Publisher");
                String publishedDate = volumeInfo.optString("publishedDate", "Unknown Date");
                String description = volumeInfo.optString("description", "No Description");

                List<String> authors = new ArrayList<>();
                JSONArray authorsArray = volumeInfo.optJSONArray("authors");
                if (authorsArray != null) {
                    for (int j = 0; j < authorsArray.length(); j++) {
                        authors.add(authorsArray.getString(j));
                    }
                }

                books.add(new Book(id, link, bookTitle, subtitle, authors, publisher, publishedDate, description));
            }

            return books;

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
