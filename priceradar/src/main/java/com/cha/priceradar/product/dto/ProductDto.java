package com.cha.priceradar.product.dto;

import com.cha.priceradar.product.domain.Product;
import com.cha.priceradar.productInfo.dto.ProductInfoDto;
import com.cha.priceradar.user.dto.UserDto;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Builder;

@Builder
public record ProductDto(
        Long productId,
        UserDto userDto,
        String name,
        String brand,
        String imageURl,
        String createdBy,
        LocalDateTime createdAt,
        String modifiedBy,
        LocalDateTime modifiedAt,
        String deletedBy,
        LocalDateTime deletedAt,
        boolean isDeleted
) {
    public static ProductDto from(Product entity){
        return ProductDto.builder()
                .productId(entity.getProductId())
                .userDto(UserDto.from(entity.getUser()))
                .name(entity.getName())
                .brand(entity.getBrand())
                .imageURl(entity.getImageUrl())
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
