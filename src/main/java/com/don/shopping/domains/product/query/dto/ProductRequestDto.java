package com.don.shopping.domains.product.query.dto;

import com.don.shopping.domains.product.domain.ProductEntity;
import com.don.shopping.domains.product.domain.ProductImageEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class ProductRequestDto {

    @NotBlank
    private String productname;
    @NotBlank
    private String productinfo;
    @NotBlank
    private int rprice;
    @NotBlank
    private int dprice;
    @NotBlank
    private int stock;

    @Builder
    public ProductRequestDto(String productname, String productinfo, int rprice, int dprice, int stock) {
        this.productname = productname;
        this.productinfo = productinfo;
        this.rprice = rprice;
        this.dprice = dprice;
        this.stock = stock;
    }

    public ProductEntity toEntity() {
        return ProductEntity.builder()
                .productname(productname)
                .productinfo(productinfo)
                .rprice(rprice)
                .dprice(dprice)
                .stock(stock)
                .build();
    }
}
