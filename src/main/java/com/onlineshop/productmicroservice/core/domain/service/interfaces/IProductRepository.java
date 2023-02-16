package com.onlineshop.productmicroservice.core.domain.service.interfaces;

import com.onlineshop.productmicroservice.core.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByProductId(Long productId);
}
