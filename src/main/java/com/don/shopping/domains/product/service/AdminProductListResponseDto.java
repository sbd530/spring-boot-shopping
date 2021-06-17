package com.don.shopping.domains.product.service;

import com.don.shopping.domains.product.domain.ProductEntity;
import lombok.Getter;

@Getter
public class AdminProductListResponseDto {
    private Long productId;
    private String productName;
    private int rprice;
    private int dprice;
    private int stock;

    public AdminProductListResponseDto(ProductEntity productEntity) {
        this.productId = productEntity.getId();
        this.productName = productEntity.getProductName();
        this.rprice = productEntity.getRprice();
        this.dprice = productEntity.getDprice();
        this.stock = productEntity.getStock();
    }
}
