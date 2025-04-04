package com.wesleybertipaglia.helpers;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import com.wesleybertipaglia.controllers.BookController;
import com.wesleybertipaglia.models.Book;
import org.json.JSONArray;

public class BookMenu {
    private static final Scanner scanner = new Scanner(System.in);
    private static final BookController bookController = new BookController();
    private static List<Book> lastResult = null;

    public static void main(String[] args) {
        while (true) {
            showMenu();
            String choice = scanner.nextLine();
            handleChoice(choice);
        }
    }

    private static void showMenu() {
        System.out.println("\n📚 BOOK SEARCH MENU");
        System.out.println("1. Search for a book");
        if (lastResult != null) System.out.println("2. Save last result to file");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }

    private static void handleChoice(String choice) {
        switch (choice) {
            case "1" -> searchBook();
            case "2" -> saveResultsToFile();
            case "0" -> exitProgram();
            default -> System.out.println("\n⚠️ Invalid option. Please try again.");
        }
    }

    private static void searchBook() {
        System.out.print("\nEnter book title: ");
        String query = scanner.nextLine();
        lastResult = bookController.getBook(query);
        displayResults(lastResult);
    }

    private static void displayResults(List<Book> books) {
        if (books.isEmpty()) {
            System.out.println("\n❌ No books found.");
            return;
        }
        System.out.println("\n📖 SEARCH RESULTS:");
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            System.out.printf("\n🔹 Book #%d\n", i + 1);
            System.out.printf("   📌 Title: %s\n", book.getTitle());
            System.out.printf("   📖 Subtitle: %s\n", book.getSubtitle().isEmpty() ? "N/A" : book.getSubtitle());
            System.out.printf("   ✍️ Authors: %s\n", book.getAuthors().isEmpty() ? "Unknown" : String.join(", ", book.getAuthors()));
            System.out.printf("   🏢 Publisher: %s\n", book.getPublisher());
            System.out.printf("   📅 Published Date: %s\n", book.getPublishedDate());
            System.out.printf("   🔗 More Info: %s\n", book.getLink());
        }
    }

    private static void saveResultsToFile() {
        if (lastResult == null || lastResult.isEmpty()) {
            System.out.println("\n⚠️ No results to save.");
            return;
        }

        String fileName = "result-" + FileHelper.getRandomFileName();
        String file = new JSONArray(lastResult).toString();
        FileHelper.saveFile(fileName, file);
    }

    private static void exitProgram() {
        System.out.println("\n👋 Exiting... Goodbye!");
        scanner.close();
        System.exit(0);
    }
}
