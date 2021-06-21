package com.don.shopping.domains.product.service;

import com.don.shopping.domains.product.domain.ProductEntity;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class AdminProductListResponseDto implements Serializable {
    private Long productId;
    private String productName;
    private Integer rprice;
    private Integer dprice;
    private Integer stock;

    public AdminProductListResponseDto(ProductEntity productEntity) {
        this.productId = productEntity.getId();
        this.productName = productEntity.getProductName();
        this.rprice = productEntity.getRprice();
        this.dprice = productEntity.getDprice();
        this.stock = productEntity.getStock();
    }
}
