package com.don.shopping.domains.product.service;

import com.don.shopping.domains.product.domain.ImageUsage;
import com.don.shopping.domains.product.domain.ProductImageEntity;
import lombok.Getter;

@Getter
public class ProductImageResponseDto {
    private Long fileId;
    private ImageUsage imageUsage;

    public ProductImageResponseDto(ProductImageEntity productImageEntity) {
        this.fileId = productImageEntity.getId();
    }
}
