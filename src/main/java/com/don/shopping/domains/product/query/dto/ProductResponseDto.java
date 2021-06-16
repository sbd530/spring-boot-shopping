package com.don.shopping.domains.product.query.dto;

import com.don.shopping.domains.product.domain.ProductEntity;
import lombok.Getter;

import java.util.List;

@Getter
public class ProductResponseDto {
    private Long productid;
    private String productname;
    private String productinfo;
    private int rprice;
    private int dprice;
    private int stock;
    private List<Long> fileId; //첨부파일 id목록

    public ProductResponseDto(ProductEntity productEntity, List<Long> fileId) {
        this.productid = productEntity.getId();
        this.productname = productEntity.getProductName();
        this.productinfo = productEntity.getProductInfo();
        this.rprice = productEntity.getRprice();
        this.dprice = productEntity.getDprice();
        this.stock = productEntity.getStock();
        this.fileId = fileId;

    }

}
