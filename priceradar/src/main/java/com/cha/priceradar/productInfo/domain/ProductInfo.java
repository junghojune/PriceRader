package com.cha.priceradar.productInfo.domain;

import com.cha.priceradar.common.domain.BaseEntity;
import com.cha.priceradar.product.domain.Product;
import jakarta.persistence.*;
import java.math.BigDecimal;

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
}
