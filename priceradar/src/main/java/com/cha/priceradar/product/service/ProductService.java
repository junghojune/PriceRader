package com.cha.priceradar.product.service;

import com.cha.priceradar.naver.dto.ItemDto;
import com.cha.priceradar.naver.service.NaverService;
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
    private final NaverService naverService;

    public Page<ProductDto> searchProduct(Long userId, Pageable pageable) {
        return productRepository.findAllByUser_UserId(userId, pageable).map(ProductDto::from);
    }

    public Page<ProductInfoDto> getProduct(Long productId, Pageable pageable) {
        return productInfoRepository.findAllByProduct_ProductId(productId, pageable).map(ProductInfoDto::from);
    }

    @Transactional
    public void createProduct(Long userId, ItemDto item ) {
        User user = userRepository.findById(userId)
                .orElseThrow();

        ProductDto productDto = ProductDto.of(item);
        Product product = productRepository.save(productDto.toEntity(user));

        updateProductInfo(product, item);
    }

    @Transactional
    public void deleteProduct(Long userId, Long productId) {
        User user = userRepository.findById(userId)
                .orElseThrow();

        Product product = productRepository.findByProductId(productId).orElseThrow();
        product.delete(user);
    }

    @Transactional
    public void updateProduct(Long userId, Long productId) {
        User user = userRepository.findById(userId)
                .orElseThrow();

        Product product = productRepository.findByProductId(productId).orElseThrow();
        ItemDto item = naverService.getItems(product.getName());

        updateProductInfo(product, item);
    }

    @Transactional
    public void updateProductInfo(Product product, ItemDto itemDto) {
        ProductInfoDto  productInfoDto = ProductInfoDto.of(itemDto);
        productInfoRepository.save(productInfoDto.toEntity(product));
    }
}
