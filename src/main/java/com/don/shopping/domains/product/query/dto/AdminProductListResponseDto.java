package com.don.shopping.domains.product.query.dto;

import com.don.shopping.domains.product.domain.ProductEntity;
import lombok.Getter;

@Getter
public class AdminProductListResponseDto {
    private Long productId;
    private String productName;
    private int rprice;
    private int dprice;
    private int stock;
    private String thumbnailImage; //썸네일 id

    public AdminProductListResponseDto(ProductEntity productEntity) {
        this.productId = productEntity.getId();
        this.productName = productEntity.getProductName();
        this.rprice = productEntity.getRprice();
        this.dprice = productEntity.getDprice();
        this.stock = productEntity.getStock();

        /*if(!productEntity.getProductImages().get(0).getSaveFileName().isEmpty()) {
            this.thumbnailImage = productEntity.getProductImages().get(0).getSaveFileName();
        }else if(productEntity.getProductImages().get(0).getSaveFileName().isEmpty()) {
            this.thumbnailImage = productEntity.getProductImages().get(1).getSaveFileName();
        }*/

        if(!productEntity.getProductImages().isEmpty()) {//첨부파일이 있으면
            this.thumbnailImage = productEntity.getProductImages().get(0).getSaveFileName(); //첫번째 이미지 반환
            if (productEntity.getProductImages().get(0).getSaveFileName().isEmpty()) {//첫번째 이미지가 없다면?
                this.thumbnailImage = productEntity.getProductImages().get(1).getSaveFileName();//두번째 이미지 반환
            }
        }

        /*if(!productEntity.getProductImages().isEmpty()) {//첨부파일 존재하면
            this.thumbnailImage = productEntity.getProductImages().get(0).getSaveFileName(); //첫번째 이미지 반환
        }else {//첨부파일 없으면
            this.thumbnailImage = productEntity.getProductImages().get(1).getSaveFileName(); //서버에 저장된 기본 이미지 반환
        }*/
    }
}
