package com.example;

import com.example.model.Product;
import com.example.service.StockService;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.concurrent.atomic.AtomicBoolean;

public class ReportApp extends Application {
    private static StockService sharedService;
    private static final AtomicBoolean isStarted = new AtomicBoolean(false);
    private PieChart pieChart;
    private BarChart<String, Number> barChart;

    @Override
    public void start(Stage stage) {
        pieChart = new PieChart();
        pieChart.setTitle("Stok Dağılımı (Adet)");

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Kâr Analizi (TL)");

        refreshData();

        // Buton stillerini daha sade ve profesyonel yaptık
        Button btnRefresh = new Button("🔄 Verileri Yenile");
        btnRefresh.setStyle("-fx-background-color: #e0e0e0; -fx-text-fill: black; -fx-font-weight: bold;");

        Button btnPdf = new Button("📄 PDF Raporu Al");
        btnPdf.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-font-weight: bold;");

        btnRefresh.setOnAction(e -> refreshData());
        btnPdf.setOnAction(e -> sharedService.exportToPDF());

        HBox charts = new HBox(20, pieChart, barChart);
        charts.setAlignment(Pos.CENTER);

        HBox buttons = new HBox(15, btnRefresh, btnPdf);
        buttons.setAlignment(Pos.CENTER);

        VBox root = new VBox(20, charts, buttons);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        // BEYAZ TEMA: Arka planı beyaz, metinleri otomatik renk yaptık
        root.setStyle("-fx-background-color: white;");

        Scene scene = new Scene(root, 1100, 650);
        stage.setScene(scene);
        stage.setTitle("StockMaster Dashboard - Klasik Görünüm");
        stage.show();
    }

    private void refreshData() {
        pieChart.getData().clear();
        barChart.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Ürün Bazlı Kâr");

        if (sharedService != null) {
            for (Product p : sharedService.getInventory()) {
                pieChart.getData().add(new PieChart.Data(p.getName(), p.getQuantity()));
                double profit = p.getQuantity() * (p.getSalePrice() - p.getPurchasePrice());
                series.getData().add(new XYChart.Data<>(p.getName(), profit));
            }
            barChart.getData().add(series);
        }
    }

    public static void openReport(StockService service) {
        sharedService = service;
        if (isStarted.get()) {
            Platform.runLater(() -> {
                try {
                    new ReportApp().start(new Stage());
                } catch (Exception e) {
                    System.err.println("Pencere açılırken hata: " + e.getMessage());
                }
            });
        } else {
            new Thread(() -> {
                try {
                    isStarted.set(true);
                    Application.launch(ReportApp.class);
                } catch (Exception e) {
                    isStarted.set(true);
                }
            }).start();
        }
    }
}