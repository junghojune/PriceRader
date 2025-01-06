package com.cha.priceradar.product.dto;

import com.cha.priceradar.productInfo.dto.ProductInfoDto;
import java.time.LocalDateTime;
import java.util.Set;

public record ProductDto(
        Long productId,
        String name,
        String brand,
        String imageURl,
        Set<ProductInfoDto> productInfos,
        String createdBy,
        LocalDateTime createdAt,
        String modifiedBy,
        LocalDateTime modifiedAt,
        String deletedBy,
        LocalDateTime deletedAt,
        boolean isDeleted
) {
}
