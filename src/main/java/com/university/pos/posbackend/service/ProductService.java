package com.university.pos.posbackend.service;


import com.university.pos.posbackend.Product;
import java.util.List;
import java.util.Optional;

// Consider adding DTOs (Data Transfer Objects) here later to separate API data from DB entities
public interface ProductService {
    Product addProduct(Product product, int initialStock); // Need initial stock now
    Optional<Product> getProductById(Integer id);
    List<Product> getAllProducts();
    Product updateProduct(Integer id, Product productDetails);
    void deleteProduct(Integer id);
    // You might want separate methods for updating stock, or handle it within updateProduct/Sales logic
    void updateStock(Integer productId, int changeInQuantity);
    int getStock(Integer productId);
}