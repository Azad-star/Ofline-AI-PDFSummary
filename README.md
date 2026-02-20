# Ofline-AI-PDFSummary
# Ofline AI PDF Summary 🚀

Bu proje, PDF dosyalarındaki metni ayıklayan ve yerel yapay zeka (Ollama) kullanarak özetleyen bir Spring Boot uygulamasıdır.

## ✨ Özellikler
* **Çevrimdışı Çalışma:** Google Gemini API kotalarına takılmadan, tamamen yerel makinede çalışır.
* **Model:** Google'ın hafif ve hızlı olan **Gemma:2b** modeli kullanılmıştır.
* **Veritabanı:** Özetler PostgreSQL veritabanına otomatik olarak kaydedilir.
* **PDF Çıktısı:** Oluşturulan özetler kullanıcıya indirilebilir PDF formatında sunulur.

## 🛠️ Teknolojiler
* Java 21 & Spring Boot 3.5.10
* Spring AI (Ollama Entegrasyonu)
* Apache PDFBox (Metin Ayıklama)
* OpenPDF (PDF Oluşturma)
* PostgreSQL

## 🚀 Kurulum
1. [Ollama](https://ollama.com) uygulamasını indirin.
2. Terminalden `ollama run gemma:2b` komutunu çalıştırın.
3. Projeyi klonlayın ve `mvn spring-boot:run` ile başlatın.
