package com.university.pos.posbackend.controller;


import com.university.pos.posbackend.Product;
import com.university.pos.posbackend.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map; // For simple requests like adding stock

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // GET /api/v1/products
    @GetMapping
    public List<Product> getAllProducts() {
        // WARNING: This currently won't include inventory details due to LAZY fetching.
        // You'd need DTOs and modify the service/repository call to fetch inventory if needed here.
        return productService.getAllProducts();
    }

    // GET /api/v1/products/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
        // WARNING: Also won't include inventory details by default.
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/v1/products/{id}/stock  (Specific endpoint to get stock)
    @GetMapping("/{id}/stock")
    public ResponseEntity<Integer> getProductStock(@PathVariable Integer id) {
        try {
            int stock = productService.getStock(id);
            return ResponseEntity.ok(stock);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


    // POST /api/v1/products
    // Need to accept initial stock - using a Wrapper/DTO is better practice
    // Simple example using Map, but a dedicated DTO class is recommended
    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Map<String, Object> payload) {
        try {
            // Basic parsing - NEEDS robust error handling and a DTO
            Product product = new Product();
            product.setName((String) payload.get("name"));
            product.setDescription((String) payload.get("description"));
            product.setPrice(new java.math.BigDecimal(payload.get("price").toString()));
            product.setCategory((String) payload.get("category"));
            product.setImageUrl((String) payload.get("imageUrl"));
            int initialStock = Integer.parseInt(payload.get("initialStock").toString());

            Product createdProduct = productService.addProduct(product, initialStock);
            // The createdProduct might not have inventory loaded, depends on service impl.
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
        } catch (Exception e) { // Catch broad exceptions - refine this
            return ResponseEntity.badRequest().build(); // Or more specific error
        }
    }


    // PUT /api/v1/products/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer id, @RequestBody Product productDetails) {
        // Note: This request body likely won't include inventory details.
        // Stock updates should happen via a dedicated endpoint or sales process.
        try {
            Product updatedProduct = productService.updateProduct(id, productDetails);
            return ResponseEntity.ok(updatedProduct);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // POST /api/v1/products/{id}/stock (Specific endpoint to update stock)
    // Example: Request body could be { "change": 10 } or { "change": -5 }
    @PostMapping("/{id}/stock")
    public ResponseEntity<Void> updateStock(@PathVariable Integer id, @RequestBody Map<String, Integer> payload) {
        try {
            Integer change = payload.get("change");
            if (change == null) {
                return ResponseEntity.badRequest().build(); // Missing 'change' field
            }
            productService.updateStock(id, change);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            // e.g., trying to make stock negative
            return ResponseEntity.badRequest().body(null); // Consider returning error message
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    // DELETE /api/v1/products/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build(); // HTTP 204
        } catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        } catch (Exception e) { // Catch other potential errors (like DB constraints if sale_items FK is RESTRICT)
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // Or INTERNAL_SERVER_ERROR
        }
    }
}
