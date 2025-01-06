package com.cha.priceradar.product.reponse;

import com.cha.priceradar.product.dto.ProductDto;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record ProductResponse(
        Long productId,
        String name,
        String brand,
        String imageUrl,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {
    public static ProductResponse from(ProductDto dto){
        return ProductResponse.builder()
                .productId(dto.productId())
                .name(dto.name())
                .brand(dto.brand())
                .imageUrl(dto.imageURl())
                .createdAt(dto.createdAt())
                .modifiedAt(dto.modifiedAt())
                .build();
    }
}
