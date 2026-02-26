package com.example;

import com.example.model.Product;
import com.example.service.StockService;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StockService service = new StockService();
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- StockMaster Pro Sistemine Hoş Geldiniz ---");

        while (true) {
            System.out.println("\n1- Ürün Ekle | 2- Listele | 3- Ara | 4- Satış Yap | 5- Sil | 6- Kritik Stok | 7- Dashboard (Grafik) | 8- Satış Geçmişi | 9- PDF Raporu | 0- Çıkış");
            System.out.print("Seçiminiz: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("İsim: "); String name = scanner.nextLine();
                    System.out.print("Miktar: "); int qty = scanner.nextInt();
                    System.out.print("Alış Fiyatı: "); double buy = scanner.nextDouble();
                    System.out.print("Satış Fiyatı: "); double sell = scanner.nextDouble();
                    System.out.print("Kritik Sınır: "); int limit = scanner.nextInt();
                    service.addProduct(new Product(name, qty, buy, sell, limit));
                    break;
                case 2: service.listInventory(); break;
                case 3:
                    System.out.print("Aranacak ürün: ");
                    service.searchProduct(scanner.nextLine());
                    break;
                case 4:
                    System.out.print("Satılacak ürün: "); String sName = scanner.nextLine();
                    System.out.print("Adet: "); int sQty = scanner.nextInt();
                    service.sellProduct(sName, sQty);
                    break;
                case 5:
                    System.out.print("Silinecek ürün: ");
                    service.deleteProduct(scanner.nextLine());
                    break;
                case 6: service.checkLowStock(); break;
                case 7: ReportApp.openReport(service); break;
                case 8: service.showSalesHistory(); break;
                case 9: service.exportToPDF(); break;
                case 0: System.exit(0);
            }
        }
    }
}

