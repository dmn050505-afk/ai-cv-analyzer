package com.nunes.ai_cv_analyzer.service;

import org.springframework.stereotype.Service;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.IOException;
import java.nio.file.Path;


@Service
public class PdfExtractionService {

    public String extractText(Path pdfPath) {

        try (PDDocument document = Loader.loadPDF(pdfPath.toFile())) {

            PDFTextStripper pdfTextStripper =
                    new PDFTextStripper();

            return pdfTextStripper.getText(document);

        } catch (IOException e) {

            throw new RuntimeException(
                    "Could not extract text from PDF",
                    e
            );

        }

    }

}