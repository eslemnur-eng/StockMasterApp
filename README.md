# 📦 StockMaster Pro: Akıllı Stok Yönetim ve Analiz Sistemi

StockMaster Pro, işletme sahiplerinin envanterlerini kolayca yönetmelerini, finansal analizlerini görselleştirmelerini ve kritik stok durumlarını takip etmelerini sağlayan, Java tabanlı bir masaüstü uygulamasıdır.

## 🚀 Öne Çıkan Özellikler

* **📊 Canlı Dashboard:** JavaFX ile geliştirilmiş, stok dağılımını (PieChart) ve potansiyel kâr analizini (BarChart) sunan interaktif yönetim paneli.
* **⚠️ Akıllı Uyarı Sistemi:** Ürün miktarı kritik sınırın altına düştüğünde sistem otomatik olarak görsel uyarı (Alert) verir.
* **📄 PDF Raporlama:** Tüm envanteri ve beklenen kâr verilerini tek tuşla profesyonel bir PDF dosyasına dönüştürür.
* **💾 Kalıcı Veri Yönetimi:** Nesne serileştirme (Serialization) kullanarak verileri `data/` klasöründe güvenle saklar.
* **💰 Finansal Analiz:** Her ürün için alış-satış farkı üzerinden potansiyel kâr hesaplaması yapar.

## 🛠️ Kullanılan Teknolojiler

* **Dil:** Java 17+
* **Arayüz:** JavaFX
* **Raporlama:** iText PDF Library
* **Proje Yönetimi:** Maven

## 📸 Ekran Görüntüleri

| Konsol Arayüzü | Görsel Dashboard (Aydınlık Tema) |
| :--- | :--- |
| ![Console Menu](https://via.placeholder.com/400x250?text=Menu+Screen) | ![Dashboard](https://via.placeholder.com/400x250?text=JavaFX+Charts) |

## ⚙️ Kurulum ve Çalıştırma

1.  Bu projeyi klonlayın:
    ```bash
    git clone [https://github.com/kullaniciadin/StockMasterApp.git](https://github.com/eslemnur-eng/StockMasterApp.git)
    ```
2.  Proje dizinine gidin:
    ```bash
    cd StockMasterApp
    ```
3.  Maven bağımlılıklarını yükleyin:
    ```bash
    mvn install
    ```
4.  Uygulamayı çalıştırın:
    ```bash
    mvn exec:java -DmainClass="com.example.Main"
    ```

## 📂 Proje Yapısı

* `com.example.model`: `Product` veri yapısı.
* `com.example.service`: İş mantığı, satış ve PDF motoru.
* `com.example.data`: Dosya okuma/yazma işlemleri (`FileHandler`).
* `com.example.ReportApp`: JavaFX görselleştirme katmanı.

---
⭐ Bu proje küçük işletmelerin dijital dönüşümüne yardımcı olmak amacıyla geliştirilmiştir.
