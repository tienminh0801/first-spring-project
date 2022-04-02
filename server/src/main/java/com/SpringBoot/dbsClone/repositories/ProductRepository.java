package com.SpringBoot.dbsClone.repositories;

import com.SpringBoot.dbsClone.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    Optional<List<Product>> findProductByNameContaining(String key);
}
