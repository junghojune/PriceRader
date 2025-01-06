package com.cha.priceradar.product.repository;

import com.cha.priceradar.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
