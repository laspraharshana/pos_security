package com.university.pos.posbackend.reporting.model;

public class InventoryReportData {
    private String productName;
    private int currentStock;
    private double unitPrice;

    public InventoryReportData(String productName, int currentStock, double unitPrice) {
        this.productName = productName;
        this.currentStock = currentStock;
        this.unitPrice = unitPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(int currentStock) {
        this.currentStock = currentStock;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "InventoryReportData{" +
                "productName='" + productName + '\'' +
                ", currentStock=" + currentStock +
                ", unitPrice=" + unitPrice +
                '}';
    }
}