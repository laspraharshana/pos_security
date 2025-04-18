package com.university.pos.posbackend.repository;


import com.university.pos.posbackend.Inventory;
import com.university.pos.posbackend.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
    // Method to find inventory by product might be useful
    Optional<Inventory> findByProduct(Product product);
    Optional<Inventory> findByProduct_ProductId(Integer productId); // Find directly by product ID
}
