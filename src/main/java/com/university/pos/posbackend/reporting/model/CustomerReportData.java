package com.university.pos.posbackend.reporting.model;

public class CustomerReportData {
    private String customerName;
    private String email;
    private int orderCount;

    public CustomerReportData(String customerName, String email, int orderCount) {
        this.customerName = customerName;
        this.email = email;
        this.orderCount = orderCount;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    @Override
    public String toString() {
        return "CustomerReportData{" +
                "customerName='" + customerName + '\'' +
                ", email='" + email + '\'' +
                ", orderCount=" + orderCount +
                '}';
    }
}