package com.don.shopping.domains.product.service;

import com.don.shopping.domains.product.domain.ProductEntity;
import com.don.shopping.domains.product.domain.ProductImageEntity;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ProductResponseDto {
    private Long productId;
    private String productName;
    private String productInfo;
    private List<String> usageList;
    private int rprice;
    private int dprice;
    private int stock;
    private List<ProductImageEntity> imageList;

    public ProductResponseDto(ProductEntity productEntity) {
        this.productId = productEntity.getId();
        this.productName = productEntity.getProductName();
        this.productInfo = productEntity.getProductInfo();
        this.rprice = productEntity.getRprice();
        this.dprice = productEntity.getDprice();
        this.stock = productEntity.getStock();
        this.usageList = productEntity.getProductImages()
                .stream()
                .map(image->image.getImageUsage().name())
                .collect(Collectors.toList());
        this.imageList = productEntity.getProductImages();

    }

}
