package com.university.pos.posbackend.controller;

import com.university.pos.posbackend.Product;
import com.university.pos.posbackend.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // GET /api/v1/products
    @GetMapping
    public List<Product> getAllProducts() {
        //  Include inventory details using the new service method
        return productService.getAllProductsWithInventory();
    }

    // GET /api/v1/products/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
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
    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Map<String, Object> payload) {
        try {
            Product product = new Product();
            product.setName((String) payload.get("name"));
            product.setDescription((String) payload.get("description"));
            product.setPrice(new java.math.BigDecimal(payload.get("price").toString()));
            product.setCategory((String) payload.get("category"));
            product.setImageUrl((String) payload.get("imageUrl"));
            int initialStock = Integer.parseInt(payload.get("initialStock").toString());

            Product createdProduct = productService.addProduct(product, initialStock);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // PUT /api/v1/products/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer id, @RequestBody Product productDetails) {
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
    @PostMapping("/{id}/stock")
    public ResponseEntity<Void> updateStock(@PathVariable Integer id, @RequestBody Map<String, Integer> payload) {
        try {
            Integer change = payload.get("change");
            if (change == null) {
                return ResponseEntity.badRequest().build();
            }
            productService.updateStock(id, change);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // DELETE /api/v1/products/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    private String trimImageUrl(String imageUrl) {
        if (imageUrl != null) {
            return imageUrl.trim();
        }
        return null;
    }
}
