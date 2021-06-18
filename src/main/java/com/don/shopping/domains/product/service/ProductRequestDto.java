package com.don.shopping.domains.product.service;

import com.don.shopping.domains.product.domain.ProductEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@NoArgsConstructor
public class ProductRequestDto {

    @NotBlank
    private String productName;
    @NotBlank
    private String productInfo;
    @NotBlank @Positive
    private int rprice;
    @Positive //dprice<rprice 프론트쪽에서 막기
    private int dprice;
    @NotBlank @Positive
    private int stock;

    @Builder
    public ProductRequestDto(String productName, String productInfo, int rprice, int dprice, int stock) {
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
