package com.cha.priceradar.product.repository;

import com.cha.priceradar.product.domain.Product;
import com.cha.priceradar.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByUser_UserId(Long user, Pageable pageable);

    Long user(User user);
}
