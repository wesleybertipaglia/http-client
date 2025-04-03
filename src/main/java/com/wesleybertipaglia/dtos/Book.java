package com.wesleybertipaglia.dtos;

import java.util.List;

public record Book(
        String id,
        String link,
        String title,
        String subtitle,
        List<String> authors,
        String publisher,
        String publishedDate,
        String description) {
}
