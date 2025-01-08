package com.cha.priceradar.product.controller;

import com.cha.priceradar.naver.dto.ItemDto;
import com.cha.priceradar.product.reponse.ProductInfoResponse;
import com.cha.priceradar.product.reponse.ProductResponse;
import com.cha.priceradar.product.service.ProductService;
import com.cha.priceradar.security.user.AuthUser;
import com.cha.priceradar.security.user.AuthUserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public Page<ProductResponse> searchProduct(
            @AuthUser AuthUserInfo userInfo,
            @PageableDefault(size = 10, sort = "createdAt", direction = Direction.DESC) Pageable pageable
    ) {
        return productService.searchProduct(userInfo.getId(), pageable).map(ProductResponse::from);
    }

    @GetMapping("/{productId}")
    public Page<ProductInfoResponse> getProduct(
            @PathVariable(value = "productId") Long productId,
            @PageableDefault(size = 10, sort = "createdAt", direction = Direction.DESC) Pageable pageable
    ) {
        return productService.getProduct(productId, pageable).map(ProductInfoResponse::from);
    }

    @PostMapping
    public void createProduct(
            @AuthUser AuthUserInfo userInfo,
            @RequestBody ItemDto product
    ) {
        productService.createProduct(userInfo.getId(), product);
    }

    @PutMapping("/{productId}")
    public void updateProduct(
            @AuthUser AuthUserInfo userInfo,
            @PathVariable(value = "productId") Long productId
    ) {
        productService.updateProduct(userInfo.getId(), productId);
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(
            @AuthUser AuthUserInfo userInfo,
            @PathVariable(value = "productId") Long productId
    ) {
        productService.deleteProduct(userInfo.getId(), productId);
    }

}
