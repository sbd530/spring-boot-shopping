package com.don.shopping.domains.product.query.dto;

import com.don.shopping.domains.product.domain.ProductEntity;
import lombok.Getter;

@Getter
public class ProductListResponseDto {
    private Long productid;
    private String productname;
    private int rprice;
    private int dprice;
    private Long thumbnailId; //썸네일 id

    public ProductListResponseDto(ProductEntity productEntity) {
        this.productid = productEntity.getId();
        this.productname = productEntity.getProductname();
        this.rprice = productEntity.getRprice();
        this.dprice = productEntity.getDprice();

        if(!productEntity.getProductImages().isEmpty()) {//첨부파일 존재하면
            this.thumbnailId = productEntity.getProductImages().get(0).getId(); //첫번째 이미지 반환
        }else {//첨부파일 없으면
            this.thumbnailId = 0L; //서버에 저장된 기본 이미지 반환
        }
    }
}
