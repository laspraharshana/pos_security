package com.university.pos.posbackend;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
// NOTE: No @Data annotation here if doing manually
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    private String category;

    @Column(name = "image_url")
    private String imageUrl;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private Inventory inventory;

    // --- MANUAL GETTERS AND SETTERS NEEDED ---

    public Integer getProductId() { return productId; }
    public void setProductId(Integer productId) { this.productId = productId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; } // <-- Method the compiler is looking for

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; } // <-- Method the compiler is looking for

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; } // <-- Method the compiler is looking for

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; } // <-- Method the compiler is looking for

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; } // <-- Method the compiler is looking for

    public Inventory getInventory() { return inventory; }
    public void setInventory(Inventory inventory) { this.inventory = inventory; }

    // You might also need constructors, equals(), hashCode(), toString() manually
}