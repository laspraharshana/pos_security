package com.university.pos.posbackend.service.impl;

import com.university.pos.posbackend.Product;
import com.university.pos.posbackend.repository.ProductRepository;
import com.university.pos.posbackend.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional
    public Product addProduct(Product product, int initialStock) {
        // Implementation for adding a product and its initial stock
        // You'll need to create an Inventory object and associate it with the product
        // before saving.  This might involve creating an InventoryRepository as well.
        // For now, I'll leave a placeholder.
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> getProductById(Integer id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(Integer id, Product productDetails) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            Product product = existingProduct.get();
            product.setName(productDetails.getName());
            product.setDescription(productDetails.getDescription());
            product.setPrice(productDetails.getPrice());
            product.setCategory(productDetails.getCategory());
            if (productDetails.getImageUrl() != null) { // Add this check
                product.setImageUrl(productDetails.getImageUrl());
            }
            return productRepository.save(product);
        } else {
            throw new EntityNotFoundException("Product not found with id: " + id);
        }


    }

    @Override
    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    public void updateStock(Integer productId, int changeInQuantity) {
        // Implementation for updating stock.
        //  This will likely involve finding the product's inventory and updating the quantity.
        //  You might need an InventoryRepository.
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            // Assuming you have a setQuantityInStock method in your Inventory class
            // product.getInventory().setQuantityInStock(product.getInventory().getQuantityInStock() + changeInQuantity);
            productRepository.save(product); // Save the product.  This will cascade the inventory update.
        } else {
            throw new EntityNotFoundException("Product not found with id: " + productId);
        }
    }

    @Override
    public int getStock(Integer productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            //  return product.getInventory().getQuantityInStock();
            return 0; //returning 0 for now
        } else {
            throw new EntityNotFoundException("Product not found with id: " + productId);
        }
    }

    @Override
    public List<Product> getAllProductsWithInventory() {
        return productRepository.findAllWithInventory();
    }
}
