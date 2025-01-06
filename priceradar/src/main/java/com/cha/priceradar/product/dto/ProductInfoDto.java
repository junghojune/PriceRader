package com.cha.priceradar.product.dto;

import com.cha.priceradar.naver.dto.ItemDto;
import com.cha.priceradar.product.domain.Product;
import com.cha.priceradar.product.domain.ProductInfo;
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

    public ProductInfo toEntity(Product product) {
        return ProductInfo.of(product, price, saleLink);
    }

    public static ProductInfoDto of(ItemDto item) {
        return ProductInfoDto.builder()
                .price(BigDecimal.valueOf(item.getLprice()))
                .saleLink(item.getLink())
                .build();
    }
}
