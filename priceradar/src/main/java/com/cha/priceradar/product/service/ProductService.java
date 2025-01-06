package com.cha.priceradar.product.service;

import static com.cha.priceradar.common.domain.UserRole.USER;

import com.cha.priceradar.common.domain.UserRole;
import com.cha.priceradar.naver.dto.ItemDto;
import com.cha.priceradar.product.domain.Product;
import com.cha.priceradar.product.dto.ProductDto;
import com.cha.priceradar.product.dto.ProductInfoDto;
import com.cha.priceradar.product.repository.ProductInfoRepository;
import com.cha.priceradar.product.repository.ProductRepository;
import com.cha.priceradar.user.domain.User;
import com.cha.priceradar.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductInfoRepository productInfoRepository;
    private final UserRepository userRepository;

    public Page<ProductDto> searchProduct(Long userId, Pageable pageable) {
        return productRepository.findAllByUser_UserId(userId, pageable).map(ProductDto::from);
    }

    @Transactional
    public void createProduct(Long userId, ItemDto item ) {
        User user = userRepository.findById(userId)
                .orElseThrow();

        ProductDto productDto = ProductDto.of(item);
        Product product = productRepository.save(productDto.toEntity(user));

        ProductInfoDto productInfoDto = ProductInfoDto.of(item);
        productInfoRepository.save(productInfoDto.toEntity(product));
    }
}
