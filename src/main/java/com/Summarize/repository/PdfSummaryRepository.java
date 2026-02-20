package com.Summarize.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Summarize.entity.PdfSummary;

@Repository
public interface PdfSummaryRepository extends JpaRepository<PdfSummary, Long> {

}
