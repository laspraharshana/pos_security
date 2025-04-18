package com.university.pos.posbackend;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import java.math.BigDecimal;

@Entity
@Table(name = "sale_items")
@Data
public class SaleItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_item_id")
    private Integer saleItemId;

    @Column(nullable = false)
    private int quantity;

    @Column(name = "item_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal itemPrice;

    // Relationship to Sale (Many SaleItems belong to One Sale)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sale_id", nullable = false) // FK column in this table
    @ToString.Exclude // Avoid recursion
    private Sale sale;

    // Relationship to Product (Many SaleItems relate to One Product)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false) // FK column in this table
    private Product product;
}
