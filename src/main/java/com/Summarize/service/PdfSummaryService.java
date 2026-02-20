package com.Summarize.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Summarize.entity.PdfSummary;
import com.Summarize.repository.PdfSummaryRepository;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional

public class PdfSummaryService {

    private final PdfSummaryRepository repository;
    private final ChatModel chatModel;
    
    // Özeti PDF'e dönüştürme
    public byte[] processAndGetSummaryPdf(MultipartFile file) throws IOException {
        
    	// 1. Önceki mantıkla metni özetle
        String summary = processAndSummarize(file); 

        // 2. Özeti PDF'e dönüştür
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, out);
        
        	//PDF içeriği oluşturma
        document.open();
        document.add(new Paragraph("AI OZET RAPORU"));
        document.add(new Paragraph("Dosya Adi: " + file.getOriginalFilename()));
        document.add(new Paragraph("\n" + summary));
        document.close();

        return out.toByteArray();
    }
    
    
    // PDF' ten alınan metinden özet çıkarma
    public String processAndSummarize(MultipartFile file) throws IOException {

        String rawText = extractTextFromPdf(file);

        if (rawText.length() > 12000) {
            rawText = rawText.substring(0, 6000);
        }

        Prompt prompt = new Prompt("SEN BİR ÖZETLEME ASİSTANIYISIN. \r\n"
        							+ "GÖREVİN: Aşağıdaki metni okumak ve metnin diline uygun bir özet çıkarmaktır. \r\n"
        							+ "KURAL: Metni asla başka bir dile çevirme. Sadece açıklayıcı bir şekilde özetle özetle.\r\n"
        							+ "METİN:\n\n" + rawText);

        ChatResponse response = chatModel.call(prompt);

        String summary = response.getResult().getOutput().getText();                        

        PdfSummary process = new PdfSummary();
        process.setFileName(file.getOriginalFilename());
        process.setSummaryResult(summary);
        process.setCreateTime(LocalDateTime.now());

        repository.save(process);
        return summary;
    }
   
    
    // PDF'ten metni çıkarma
    private String extractTextFromPdf(MultipartFile file) throws IOException {
        try (PDDocument document = Loader.loadPDF(file.getBytes())) {
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document);
        }
    }
}
