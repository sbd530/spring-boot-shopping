package com.don.shopping.domains.product.query.dto;

import com.don.shopping.domains.product.domain.ProductImageEntity;
import lombok.Getter;

@Getter
public class ProductImageResponseDto {
    private Long fileId;//파일id

    public ProductImageResponseDto(ProductImageEntity productImageEntity) {
        this.fileId = productImageEntity.getId();
    }
}
