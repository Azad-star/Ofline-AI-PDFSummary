package com.Summarize.controller;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Summarize.service.PdfSummaryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/pdf")
@RequiredArgsConstructor
public class PdfSummaryController {

    private final PdfSummaryService service;

    @PostMapping("/download-summary")
    public ResponseEntity<byte[]> downloadSummary(@RequestParam("file") MultipartFile file) throws IOException {
        byte[] pdfContent = service.processAndGetSummaryPdf(file);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ozet.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfContent);
    }
}