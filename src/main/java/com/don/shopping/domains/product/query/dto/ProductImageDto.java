package com.don.shopping.domains.product.query.dto;

import com.don.shopping.domains.product.domain.ProductImageEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class ProductImageDto {
    @NotBlank
    private String originalfilename;
    @NotBlank
    private String filePath;
    @NotBlank
    private String imageUsage;
    @NotBlank
    private Long fileSize;

    @Builder
    public ProductImageDto(String originalfilename, String filePath, String imageUsage, Long fileSize) {
        this.originalfilename = originalfilename;
        this.filePath = filePath;
        this.imageUsage = imageUsage;
        this.fileSize = fileSize;
    }

    public ProductImageEntity toEntity(){
        ProductImageEntity build = ProductImageEntity.builder()
                                .originalfilename(originalfilename)
                                .filePath(filePath)
                                .imageUsage(imageUsage)
                                .fileSize(fileSize)
                                .build();

        return build;
    }
}
