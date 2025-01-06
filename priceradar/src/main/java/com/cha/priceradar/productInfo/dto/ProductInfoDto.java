package com.cha.priceradar.productInfo.dto;

import com.cha.priceradar.productInfo.domain.ProductInfo;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record ProductInfoDto(
        Long productInfoId,
        Long productId,
        BigDecimal price,
        String saleLink,
        String createdBy,
        LocalDateTime createdAt,
        String modifiedBy,
        LocalDateTime modifiedAt,
        String deletedBy,
        LocalDateTime deletedAt,
        boolean isDeleted
) {
    public static ProductInfoDto from(ProductInfo entity) {
        return ProductInfoDto.builder()
                .productInfoId(entity.getProductInfoId())
                .productId(entity.getProduct().getProductId())
                .price(entity.getPrice())
                .saleLink(entity.getSaleLink())
                .createdBy(entity.getCreatedBy())
                .createdAt(entity.getCreatedAt())
                .modifiedBy(entity.getModifiedBy())
                .modifiedAt(entity.getModifiedAt())
                .deletedBy(entity.getDeletedBy())
                .deletedAt(entity.getDeletedAt())
                .isDeleted(entity.isDeleted())
                .build();
    }
}
