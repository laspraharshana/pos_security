package com.university.pos.posbackend.repository;


import com.university.pos.posbackend.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// Use Integer for the ID type to match the entity
public interface ProductRepository extends JpaRepository<Product, Integer> {
    // Add custom query methods if needed, e.g., findByName, findByCategory
}