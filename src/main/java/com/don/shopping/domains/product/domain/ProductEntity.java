package com.don.shopping.domains.product.domain;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name="product")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(
        name = "product_seq_generator",
        sequenceName = "product_seq", // 매핑할 데이터베이스 시퀀스 이름
        initialValue = 1,
        allocationSize = 1)
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq_generator")
    private Long id;

    private String productName;

    private String productInfo;

    private int rprice;

    private int dprice;

    private int stock;

    @OneToMany(
            mappedBy = "product", targetEntity = ProductImageEntity.class,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true
    )
    private List<ProductImageEntity> productImages = new ArrayList<>();

    @Builder
    public ProductEntity(String productName, String productInfo, int rprice, int dprice, int stock) {
        this.productName = productName;
        this.productInfo = productInfo;
        this.rprice = rprice;
        this.dprice = dprice;
        this.stock = stock;
    }

    public void update(String productName, String productInfo, int rprice, int dprice, int stock) {
        this.productName = productName;
        this.productInfo = productInfo;
        this.rprice = rprice;
        this.dprice = dprice;
        this.stock = stock;
    }

    //ProductEntity에서 파일 처리 위함
    public void addImage(ProductImageEntity productImageEntity) {
        this.productImages.add(productImageEntity);

        //상품에 파일이 저장되어있지 않은 경우
        if(productImageEntity.getProduct() != this){
            //파일저장
            productImageEntity.saveProductEntity(this);
        }
    }
}
