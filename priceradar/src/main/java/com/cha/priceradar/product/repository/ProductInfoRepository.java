package com.cha.priceradar.product.repository;

import com.cha.priceradar.product.domain.Product;
import com.cha.priceradar.product.domain.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductInfoRepository extends JpaRepository<ProductInfo, Long> {
    Page<ProductInfo> findAllByProduct_ProductId(Long productId, Pageable pageable);

    Long product(Product product);
}
