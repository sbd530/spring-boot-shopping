package com.don.shopping.domains.product.query.dto;

import com.don.shopping.domains.product.domain.ProductEntity;
import lombok.Getter;

@Getter
public class ProductListResponseDto {

    private Long productid;
    private String productname;
    private String productinfo;
    private int rprice;
    private int dprice;
    private int discount;
    private int stock;
    private String thumbnailImage; //썸네일 id

    public ProductListResponseDto(ProductEntity productEntity) {
        this.productid = productEntity.getId();
        this.productname = productEntity.getProductName();
        this.productinfo = productEntity.getProductInfo();
        this.rprice = productEntity.getRprice();
        this.dprice = productEntity.getDprice();
        this.discount = (rprice-dprice)/rprice*100;
        this.stock = productEntity.getStock();

        if(!productEntity.getProductImages().isEmpty()) {//첨부파일이 있으면
            this.thumbnailImage = productEntity.getProductImages().get(0).getSaveFileName(); //첫번째 이미지 반환
            if (productEntity.getProductImages().get(0).getSaveFileName().isEmpty()) {//첫번째 이미지가 없다면?
                this.thumbnailImage = productEntity.getProductImages().get(1).getSaveFileName();//두번째 이미지 반환
            }
        }
    }
}
