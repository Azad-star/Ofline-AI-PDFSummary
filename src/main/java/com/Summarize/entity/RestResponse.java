package com.Summarize.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestResponse<T> {
    private T data;             // Yapay zekadan gelen özet veya dosya bilgisi
    private String message;     // "İşlem başarılı" gibi mesajlar
    private boolean success;    // İşlemin durumu
}