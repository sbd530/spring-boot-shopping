package com.don.shopping.domains.product.query.dto;

import com.don.shopping.domains.product.domain.ProductImageEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateProductImageDto {

    private Long id;
    private String saveFileName;
    private Long fileSize;

    public UpdateProductImageDto (ProductImageEntity imageEntity) {
        this.id = imageEntity.getId();
        this.saveFileName = imageEntity.getSaveFileName();
        this.fileSize = imageEntity.getFileSize();
    }
}
