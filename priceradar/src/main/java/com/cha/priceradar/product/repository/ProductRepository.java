package com.cha.priceradar.product.repository;

import com.cha.priceradar.product.domain.Product;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByUser_UserId(Long user, Pageable pageable);

    Optional<Product> findByProductId(Long productId);
}
