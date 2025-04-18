package com.university.pos.posbackend; // Make sure this package matches your project structure

import jakarta.persistence.*;
// Removed: import lombok.Data; // No longer using Lombok's @Data
import lombok.ToString; // Keep this if you might use Lombok's @ToString elsewhere or need to exclude fields from manual toString

@Entity
@Table(name = "inventory")
// Removed: @Data // Replaced with manual methods below
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private Integer inventoryId;

    @Column(name = "quantity_in_stock", nullable = false)
    private int quantityInStock;

    // Relationship to Product (One Inventory record belongs to One Product)
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false, unique = true) // FK column name
    @ToString.Exclude // Exclude from Lombok toString to prevent recursion with Product
    private Product product;

    // --- Manual Getters and Setters ---

    public Integer getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    // Note: If you are not using Lombok at all, you might also need to manually add:
    // - Constructors (default and potentially parameterized)
    // - equals() and hashCode() methods (important for JPA entity management)
    // - toString() method (useful for debugging)
}