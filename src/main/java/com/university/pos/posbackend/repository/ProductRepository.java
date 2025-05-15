package com.university.pos.posbackend.repository;


import com.university.pos.posbackend.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
// Use Integer for the ID type to match the entity
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p LEFT JOIN FETCH p.inventory")
    List<Product> findAllWithInventory();

    //  ... other methods ...
}