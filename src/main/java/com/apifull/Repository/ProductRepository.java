package com.apifull.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apifull.product.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

