package com.cha.priceradar.product.domain;

import com.cha.priceradar.common.domain.BaseEntity;
import com.cha.priceradar.naver.dto.ItemDto;
import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@Entity
@Table(name = "pr_product_Info")
public class ProductInfo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_info_id")
    private Long productInfoId;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "sale_link")
    private String saleLink;

    public static ProductInfo of(Product product, BigDecimal price, String saleLink) {
        return ProductInfo.builder()
                .product(product)
                .price(price)
                .saleLink(saleLink)
                .build();
    }
}
