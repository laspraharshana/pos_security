package com.university.pos.posbackend; // Ensure this package matches your project structure

import jakarta.persistence.*;
// Removed: import lombok.Data; // No longer using Lombok's @Data
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "sales")
// Removed: @Data // Replaced with manual methods below
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_id")
    private Integer saleId;

    @Column(name = "sale_date")
    private LocalDateTime saleDate;

    @Column(name = "total_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAmount;

    // Relationship to SaleItem (One Sale has Many SaleItems)
    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SaleItem> saleItems; // Getter and Setter needed for this list too

    @PrePersist // This is standard JPA, keep it
    protected void onCreate() {
        saleDate = LocalDateTime.now();
    }

    // --- Manual Getters and Setters ---

    public Integer getSaleId() {
        return saleId;
    }

    public void setSaleId(Integer saleId) {
        this.saleId = saleId;
    }

    public LocalDateTime getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDateTime saleDate) {
        this.saleDate = saleDate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<SaleItem> getSaleItems() {
        return saleItems;
    }

    public void setSaleItems(List<SaleItem> saleItems) {
        this.saleItems = saleItems;
    }

    // Note: As before, if you are not using Lombok at all, you might also need to manually add:
    // - Constructors (default and potentially parameterized)
    // - equals() and hashCode() methods
    // - toString() method
}