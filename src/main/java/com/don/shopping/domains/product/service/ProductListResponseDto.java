package com.don.shopping.domains.product.service;

import com.don.shopping.domains.product.domain.ProductEntity;
import lombok.Getter;

@Getter
public class ProductListResponseDto {

    private Long productId;
    private String productName;
    private int rprice;
    private int dprice;
    private int discount;
    private String thumbnailImage1; //썸네일1 saveFileName
    private String thumbnailImage2; //썸네일2 saveFileName

    public ProductListResponseDto(ProductEntity productEntity) {
        this.productId = productEntity.getId();
        this.productName = productEntity.getProductName();
        this.rprice = productEntity.getRprice();
        this.dprice = productEntity.getDprice();
        this.discount = (rprice-dprice)/rprice*100;

        if(!productEntity.getProductImages().isEmpty()) {//첨부파일이 있으면
            this.thumbnailImage1 = productEntity.getProductImages().get(0).getSaveFileName(); //첫번째 이미지 반환
            //if (productEntity.getProductImages().get(0).getSaveFileName().isEmpty()) {//첫번째 이미지가 없다면?
            this.thumbnailImage2 = productEntity.getProductImages().get(1).getSaveFileName();//두번째 이미지 반환
            //}
        }
    }
}
