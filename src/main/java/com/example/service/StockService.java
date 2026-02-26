package com.example.service;

import com.example.data.FileHandler;
import com.example.model.Product;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StockService {
    private List<Product> inventory;

    public StockService() {
        this.inventory = FileHandler.loadProducts();
    }

    public void addProduct(Product p) {
        inventory.add(p);
        FileHandler.saveProducts(inventory);
        System.out.println("Başarılı!");
    }

    public void listInventory() {
        if (inventory.isEmpty()) System.out.println("Envanter boş.");
        else inventory.forEach(System.out::println);
    }

    public void searchProduct(String name) {
        inventory.stream()
                .filter(p -> p.getName().toLowerCase().contains(name.toLowerCase()))
                .forEach(System.out::println);
    }

    public void sellProduct(String name, int qty) {
        for (Product p : inventory) {
            if (p.getName().equalsIgnoreCase(name) && p.getQuantity() >= qty) {
                p.setQuantity(p.getQuantity() - qty);
                FileHandler.saveProducts(inventory);
                double profit = qty * (p.getSalePrice() - p.getPurchasePrice());
                FileHandler.logSale(LocalDateTime.now() + " - " + name + " satıldı. Kâr: " + profit);
                System.out.println("Satış tamamlandı.");
                return;
            }
        }
        System.out.println("Ürün bulunamadı veya yetersiz stok.");
    }

    public void deleteProduct(String name) {
        if (inventory.removeIf(p -> p.getName().equalsIgnoreCase(name))) {
            FileHandler.saveProducts(inventory);
            System.out.println("Ürün silindi.");
        }
    }

    public void checkLowStock() {
        inventory.stream()
                .filter(p -> p.getQuantity() <= p.getLowStockLimit())
                .forEach(p -> System.out.println("⚠️ KRİTİK: " + p.getName() + " (" + p.getQuantity() + ")"));
    }

    public void showSalesHistory() {
        FileHandler.loadSalesHistory().forEach(System.out::println);
    }

    public void exportToPDF() {
        Document document = new Document();
        try {
            String fileName = "Stok_Raporu_" + System.currentTimeMillis() + ".pdf";
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();
            document.add(new Paragraph("STOCKMASTER ENVANTER RAPORU\n\n"));
            PdfPTable table = new PdfPTable(4);
            table.addCell("Ürün"); table.addCell("Stok"); table.addCell("Satış Fiyatı"); table.addCell("Potansiyel Kâr");
            for (Product p : inventory) {
                table.addCell(p.getName());
                table.addCell(String.valueOf(p.getQuantity()));
                table.addCell(String.valueOf(p.getSalePrice()));
                table.addCell(String.valueOf(p.getQuantity() * (p.getSalePrice() - p.getPurchasePrice())));
            }
            document.add(table);
            document.close();
            System.out.println("PDF oluşturuldu: " + fileName);
        } catch (Exception e) { System.err.println("PDF Hatası: " + e.getMessage()); }
    }

    public List<Product> getInventory() { return inventory; }
}