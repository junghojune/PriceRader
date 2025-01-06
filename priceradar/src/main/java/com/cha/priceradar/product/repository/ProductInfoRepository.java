package com.cha.priceradar.product.repository;

import com.cha.priceradar.product.domain.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductInfoRepository extends JpaRepository<ProductInfo, Long> {
}
