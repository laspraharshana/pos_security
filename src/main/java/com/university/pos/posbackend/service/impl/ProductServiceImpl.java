package com.university.pos.posbackend.service.impl;


import com.university.pos.posbackend.Inventory;
import com.university.pos.posbackend.Product;
import com.university.pos.posbackend.repository.InventoryRepository;
import com.university.pos.posbackend.repository.ProductRepository;
import com.university.pos.posbackend.service.ProductService;
import jakarta.persistence.EntityNotFoundException; // More specific exception
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private InventoryRepository inventoryRepository; // Inject Inventory repository

    @Override
    @Transactional // Important for multiple DB operations
    public Product addProduct(Product product, int initialStock) {
        // Ensure product doesn't have an ID yet if it's new
        product.setProductId(null); // Or handle update logic if ID exists
        Product savedProduct = productRepository.save(product);

        // Create and save the associated inventory record
        Inventory inventory = new Inventory();
        inventory.setProduct(savedProduct); // Link inventory to the saved product
        inventory.setQuantityInStock(initialStock);
        inventoryRepository.save(inventory);

        // Set the inventory back on the product object if needed for the return value
        // (may not be necessary depending on how you use the returned object)
        // savedProduct.setInventory(inventory); -> Be careful with bidirectional relationships

        return savedProduct; // Return the saved product (without necessarily having inventory loaded)
    }

    @Override
    public Optional<Product> getProductById(Integer id) {
        // Note: This won't load inventory by default due to FetchType.LAZY
        return productRepository.findById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        // Note: This won't load inventory by default
        return productRepository.findAll();
    }

    @Override
    @Transactional
    public Product updateProduct(Integer id, Product productDetails) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));

        // Update product fields
        existingProduct.setName(productDetails.getName());
        existingProduct.setDescription(productDetails.getDescription());
        existingProduct.setPrice(productDetails.getPrice());
        existingProduct.setCategory(productDetails.getCategory());
        existingProduct.setImageUrl(productDetails.getImageUrl());
        // DO NOT update inventory here directly unless intended. Use updateStock method.

        return productRepository.save(existingProduct);
    }

    @Override
    @Transactional
    public void deleteProduct(Integer id) {
        // Thanks to CascadeType.ALL and the FK constraint ON DELETE CASCADE,
        // deleting the product should also delete the associated inventory record.
        // Verify this behavior based on your final FK constraint.
        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateStock(Integer productId, int changeInQuantity) {
        Inventory inventory = inventoryRepository.findByProduct_ProductId(productId)
                .orElseThrow(() -> new EntityNotFoundException("Inventory not found for product id: " + productId));

        int newStock = inventory.getQuantityInStock() + changeInQuantity;
        if (newStock < 0) {
            throw new IllegalArgumentException("Stock cannot go below zero."); // Basic validation
        }
        inventory.setQuantityInStock(newStock);
        inventoryRepository.save(inventory);
    }

    @Override
    public int getStock(Integer productId) {
        Inventory inventory = inventoryRepository.findByProduct_ProductId(productId)
                .orElseThrow(() -> new EntityNotFoundException("Inventory not found for product id: " + productId));
        return inventory.getQuantityInStock();
    }
}