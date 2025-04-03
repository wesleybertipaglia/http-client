package com.wesleybertipaglia.menu;

import java.util.List;
import java.util.Scanner;

import com.wesleybertipaglia.controllers.BookController;
import com.wesleybertipaglia.dtos.Book;

public class BookMenu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookController bookController = new BookController();

        while (true) {
            System.out.println("\n📚 BOOK SEARCH MENU");
            System.out.println("1. Search for a book");
            System.out.println("2. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("\nEnter book title: ");
                    String query = scanner.nextLine();
                    List<Book> books = bookController.getBook(query);

                    if (books.isEmpty()) {
                        System.out.println("\n❌ No books found.");
                    } else {
                        System.out.println("\n📖 SEARCH RESULTS:");
                        for (int i = 0; i < books.size(); i++) {
                            Book book = books.get(i);
                            System.out.println("\n🔹 Book #" + (i + 1));
                            System.out.println("   📌 Title: " + book.title());
                            System.out.println(
                                    "   📖 Subtitle: " + (book.subtitle().isEmpty() ? "N/A" : book.subtitle()));
                            System.out.println("   ✍️ Authors: "
                                    + (book.authors().isEmpty() ? "Unknown" : String.join(", ", book.authors())));
                            System.out.println("   🏢 Publisher: " + book.publisher());
                            System.out.println("   📅 Published Date: " + book.publishedDate());
                            System.out.println("   🔗 More Info: " + book.link());
                        }
                    }
                    break;

                case "2":
                    System.out.println("\n👋 Exiting... Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("\n⚠️ Invalid option. Please try again.");
            }
        }
    }
}
