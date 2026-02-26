package com.example.model;

import java.io.Serializable;

public class Product implements Serializable {
    private String name;
    private int quantity;
    private double purchasePrice;
    private double salePrice;
    private int lowStockLimit; // Kritik stok sınırı

    public Product(String name, int quantity, double purchasePrice, double salePrice, int lowStockLimit) {
        this.name = name;
        this.quantity = quantity;
        this.purchasePrice = purchasePrice;
        this.salePrice = salePrice;
        this.lowStockLimit = lowStockLimit; //
    }

    // Getter Metodları (Verilere güvenli erişim sağlar)
    public String getName() { return name; }

    public int getQuantity() { return quantity; }

    public double getPurchasePrice() { return purchasePrice; }

    public double getSalePrice() { return salePrice; }

    public int getLowStockLimit() { return lowStockLimit; } // ReportApp'in hata vermesini engelleyen metod

    // Setter Metodları (Verileri güncellemek için kullanılır)
    public void setQuantity(int quantity) { this.quantity = quantity; }

    @Override
    public String toString() {
        return String.format("Ürün: %s | Stok: %d | Alış: %.2f | Satış: %.2f | Sınır: %d",
                name, quantity, purchasePrice, salePrice, lowStockLimit);
    }
}