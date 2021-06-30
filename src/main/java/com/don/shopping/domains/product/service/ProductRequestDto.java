package com.don.shopping.domains.product.service;

import com.don.shopping.domains.product.domain.ProductEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@NoArgsConstructor
public class ProductRequestDto {

    @NotBlank
    private String productName;
    @NotBlank
    private String productInfo;
    @Positive
    private Integer rprice;
    @Positive
    private Integer dprice;
    @Positive
    private Integer stock;

    @Builder
    public ProductRequestDto(String productName, String productInfo, Integer rprice, Integer dprice, Integer stock) {
        this.productName = productName;
        this.productInfo = productInfo;
        this.rprice = rprice;
        this.dprice = dprice;
        this.stock = stock;
    }

    public ProductEntity toEntity() {
        return ProductEntity.builder()
                .productName(productName)
                .productInfo(productInfo)
                .rprice(rprice)
                .dprice(dprice)
                .stock(stock)
                .build();
    }
}
