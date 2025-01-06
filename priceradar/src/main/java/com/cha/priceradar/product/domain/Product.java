package com.cha.priceradar.product.domain;

import com.cha.priceradar.common.domain.BaseEntity;
import com.cha.priceradar.user.domain.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
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
@Table(name = "pr_product")
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "image_url")
    private String imageUrl;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<ProductInfo> productInfos = new LinkedHashSet<>();

    public static Product of(User user, String name, String brand, String imageURl) {
        return Product.builder()
                .user(user)
                .name(name)
                .brand(brand)
                .imageUrl(imageURl)
                .build();
    }

    public void delete(User user) {
        setIsDeleted(true);
        setDeletedAt(LocalDateTime.now());
        setDeletedBy(user.getEmail());
        this.productInfos.forEach(productInfo -> productInfo.delete(user));
    }
}

