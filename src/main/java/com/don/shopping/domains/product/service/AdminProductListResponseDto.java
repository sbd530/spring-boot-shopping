package com.don.shopping.domains.product.service;

import com.don.shopping.domains.product.domain.ProductEntity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Setter
@RedisHash("adminProductListResponseDto")
@NoArgsConstructor
public class AdminProductListResponseDto {

    @Id
    private Long id;

    private Long productId;
    private String productName;
    private String productInfo;
    private Integer rprice;
    private Integer dprice;
    private Integer stock;

    public AdminProductListResponseDto(ProductEntity productEntity) {
        this.productId = productEntity.getId();
        this.productName = productEntity.getProductName();
        this.productInfo = productEntity.getProductInfo();
        this.rprice = productEntity.getRprice();
        this.dprice = productEntity.getDprice();
        this.stock = productEntity.getStock();
    }
}
