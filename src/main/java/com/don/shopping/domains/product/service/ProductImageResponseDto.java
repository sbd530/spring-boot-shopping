package com.don.shopping.domains.product.service;

import com.don.shopping.domains.product.domain.ImageUsage;
import com.don.shopping.domains.product.domain.ProductImageEntity;
import lombok.Getter;

@Getter
public class ProductImageResponseDto {
    private Long fileId;//파일id
    private ImageUsage imageUsage;//용도

    public ProductImageResponseDto(ProductImageEntity productImageEntity) {
        this.fileId = productImageEntity.getId();
    }
}
