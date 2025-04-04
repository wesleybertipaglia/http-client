package com.wesleybertipaglia.helpers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class FileHelper {
    private static final String DIRECTORY = "saved_files";

    public static String getRandomFileName() {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
        String uuid = UUID.randomUUID().toString().substring(0, 8);
        return timestamp + "-" + uuid + ".json";
    }

    public static void saveFile(String fileName, String fileContent) {
        try {
            Files.createDirectories(Paths.get(DIRECTORY));
            try (FileWriter fileWriter = new FileWriter(DIRECTORY + "/" + fileName)) {
                fileWriter.write(fileContent);
                System.out.println("\n✅ Results saved successfully as " + fileName);
            }
        } catch (IOException e) {
            System.out.println("\n❌ Error saving file: " + e.getMessage());
        }
    }
}
