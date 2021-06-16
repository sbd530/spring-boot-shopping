package com.don.shopping.domains.product.query.dto;

import com.don.shopping.domains.product.domain.ImageUsage;
import com.don.shopping.domains.product.domain.ProductImageEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class ProductImageDto {
    @NotBlank
    private String originalFileName;
    @NotBlank
    private String saveFileName;
    @NotBlank
    private String filePath;
    @NotBlank
    private ImageUsage imageUsage;
    @NotBlank
    private Long fileSize;

    @Builder
    public ProductImageDto(String originalFileName, String saveFileName, String filePath, ImageUsage imageUsage, Long fileSize) {
        this.originalFileName = originalFileName;
        this.saveFileName = saveFileName;
        this.filePath = filePath;
        this.imageUsage = imageUsage;
        this.fileSize = fileSize;
    }

    public ProductImageEntity toEntity(){
        ProductImageEntity build = ProductImageEntity.builder()
                                .originalFileName(originalFileName)
                                .saveFileName(saveFileName)
                                .filePath(filePath)
                                .imageUsage(imageUsage)
                                .fileSize(fileSize)
                                .build();

        return build;
    }
}
