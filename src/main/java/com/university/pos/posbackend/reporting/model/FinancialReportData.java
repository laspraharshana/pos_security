package com.university.pos.posbackend.reporting.model;

import java.math.BigDecimal;

public class FinancialReportData {
    private String itemName;
    private BigDecimal amount;

    public FinancialReportData(String itemName, BigDecimal amount) {
        this.itemName = itemName;
        this.amount = amount;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "FinancialReportData{" +
                "itemName='" + itemName + '\'' +
                ", amount=" + amount +
                '}';
    }
}