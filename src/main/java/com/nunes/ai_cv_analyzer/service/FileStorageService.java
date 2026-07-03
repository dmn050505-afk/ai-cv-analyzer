package com.nunes.ai_cv_analyzer.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageService {

    private static final String UPLOAD_DIRECTORY = "uploads";

    @PostConstruct
    public void init() {

        try {

            Path uploadPath = Paths.get(UPLOAD_DIRECTORY);

            Files.createDirectories(uploadPath);

        } catch (IOException e) {

            throw new RuntimeException(
                    "Could not create upload directory",
                    e
            );

        }

    }
    public String storeFile(Long candidateId,
                            MultipartFile file) {
        if (file.isEmpty()) {

            throw new RuntimeException(
                    "File cannot be empty"
            );

        }

        if (!"application/pdf".equals(file.getContentType())) {

            throw new RuntimeException(
                    "Only PDF files are allowed"
            );

        }
        try {

            Path candidateDirectory = Paths.get(
                    UPLOAD_DIRECTORY,
                    "cvs",
                    candidateId.toString()
            );

            Files.createDirectories(candidateDirectory);

            String fileName =
                    UUID.randomUUID() + ".pdf";

            Path targetLocation =
                    candidateDirectory.resolve(fileName);

            Files.copy(
                    file.getInputStream(),
                    targetLocation,
                    StandardCopyOption.REPLACE_EXISTING
            );

            return targetLocation.toString();

        } catch (IOException e) {

            throw new RuntimeException(
                    "Could not store file",
                    e
            );

        }

    }

}