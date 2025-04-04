package com.wesleybertipaglia.controllers;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.wesleybertipaglia.models.Book;

public class BookController {
    private static final String API_URL = "https://www.googleapis.com/books/v1";
    private final HttpController httpController;

    public BookController() {
        this.httpController = new HttpController();
    }

    public List<Book> getBook(String title) {
        String encodedTitle = URLEncoder.encode(title, StandardCharsets.UTF_8);
        String url = API_URL + "/volumes?q=" + encodedTitle;

        try {
            String response = httpController.get(url).body();
            if (response == null) throw new Exception("Response is null");

            return parseBooks(response);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private List<Book> parseBooks(String jsonResponse) {
        JSONObject json = new JSONObject(jsonResponse);
        JSONArray items = json.optJSONArray("items");
        if (items == null) return new ArrayList<>();

        List<Book> books = new ArrayList<>();
        for (int i = 0; i < items.length(); i++) {
            books.add(parseBook(items.getJSONObject(i)));
        }
        return books;
    }

    private Book parseBook(JSONObject itemJson) {
        JSONObject volumeInfo = itemJson.optJSONObject("volumeInfo");
        if (volumeInfo == null) return new Book("N/A", "N/A", "No Title", "", new ArrayList<>(), "Unknown Publisher", "Unknown Date", "No Description");

        return new Book(
                itemJson.optString("id", "N/A"),
                itemJson.optString("selfLink", "N/A"),
                volumeInfo.optString("title", "No Title"),
                volumeInfo.optString("subtitle", ""),
                parseAuthors(volumeInfo.optJSONArray("authors")),
                volumeInfo.optString("publisher", "Unknown Publisher"),
                volumeInfo.optString("publishedDate", "Unknown Date"),
                volumeInfo.optString("description", "No Description")
        );
    }

    private List<String> parseAuthors(JSONArray authorsArray) {
        List<String> authors = new ArrayList<>();
        if (authorsArray != null) {
            for (int j = 0; j < authorsArray.length(); j++) {
                authors.add(authorsArray.optString(j, "Unknown"));
            }
        }
        return authors;
    }
}
