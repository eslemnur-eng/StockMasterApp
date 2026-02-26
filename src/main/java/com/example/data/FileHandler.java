package com.example.data;

import com.example.model.Product;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandler {
    // Proje ana dizininde 'data' klasörünü hedefliyoruz
    private static final String FILE_PATH = "data/inventory.txt";
    private static final String LOG_PATH = "data/sales_history.txt";

    // Envanteri Dosyaya Kaydet
    public static void saveProducts(List<Product> products) {
        ensureDataFolderExists();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(products);
        } catch (IOException e) {
            System.err.println("Dosya yazma hatası: " + e.getMessage());
        }
    }

    // Envanteri Dosyadan Yükle
    @SuppressWarnings("unchecked")
    public static List<Product> loadProducts() {
        ensureDataFolderExists();
        File file = new File(FILE_PATH);

        // Dosya yoksa yeni bir liste döndür
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            return (List<Product>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // Hata varsa terminalde göster
            System.err.println("Veri okuma hatası: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Satış Logunu Kaydet
    public static void logSale(String log) {
        ensureDataFolderExists();
        try (PrintWriter out = new PrintWriter(new FileWriter(LOG_PATH, true))) {
            out.println(log);
        } catch (IOException e) {
            System.err.println("Log yazma hatası: " + e.getMessage());
        }
    }

    // Satış Geçmişini Yükle
    public static List<String> loadSalesHistory() {
        ensureDataFolderExists();
        List<String> history = new ArrayList<>();
        File file = new File(LOG_PATH);
        if (!file.exists()) return history;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                history.add(scanner.nextLine());
            }
        } catch (IOException e) {
            System.err.println("Satış geçmişi okunurken hata: " + e.getMessage());
        }
        return history;
    }

    // Klasör Kontrolü (Hataları önlemek için kritik)
    private static void ensureDataFolderExists() {
        File folder = new File("data");
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }
}