package com.cha.priceradar.product.reponse;

import com.cha.priceradar.product.dto.ProductDto;
import com.cha.priceradar.product.dto.ProductInfoDto;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record ProductInfoResponse(
        BigDecimal price,
        String saleLink,
        LocalDateTime createdAt
) {
    public static ProductInfoResponse from(ProductInfoDto dto){
        return ProductInfoResponse.builder()
                .price(dto.price())
                .saleLink(dto.saleLink())
                .createdAt(dto.createdAt())
                .build();
    }
}
