package com.cha.priceradar.product.service;

import com.cha.priceradar.product.dto.ProductDto;
import com.cha.priceradar.product.reponse.ProductResponse;
import com.cha.priceradar.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Page<ProductDto> searchProduct(Long userId, Pageable pageable) {
        return productRepository.findAllByUser_UserId(userId, pageable).map(ProductDto::from);
    }
}
