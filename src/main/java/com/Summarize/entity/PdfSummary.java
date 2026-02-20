package com.Summarize.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "summarize")
public class PdfSummary {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file_name")
    private String fileName; // Yüklenen PDF'in orijinal adı

    @Column(name = "summary_result", columnDefinition = "TEXT")
    private String summaryResult; // Yapay zekanın ürettiği özet metin

    @Column(name = "create_time")
    private LocalDateTime createTime; // İşlemin yapıldığı tarih ve saat
	
}
