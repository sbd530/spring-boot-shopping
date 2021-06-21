package com.don.shopping.domains.product.domain;


import com.don.shopping.common.exception.StockShortageException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name="product")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    /**
     * increaseStock(), decreaseStock()
     * Order 를 추가하거나 주문을 취소하는 경우, 해당 제품의 stock 을 증가, 감소 시킵니다.
     * 작성자 : 윤병돈
     */
    public void increaseStock(int amount) {
        this.stock += amount;
    }
    public void decreaseStock(int amount) {
        if(amount > this.stock)
            throw new StockShortageException();
        this.stock = this.stock - amount;
    }
}