package com.don.shopping.domains.review.domain;

import com.don.shopping.domains.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;



@Entity
@Table(name="review")
@Getter
@NoArgsConstructor
public class ReviewEntity {

    @Id @GeneratedValue
    private Long reviewid; //리뷰의 id(pk)

    @ManyToOne//1명이 여러개의 리뷰를 달수 있기때문에 //맞나?
    @JoinColumn(name="userid") //미지정
    private UserEntity reviewer; //리뷰 올린사람

    @Embedded
    private Product product;

    @Builder
    public ReviewEntity(Long reviewid,Product product){
        this.reviewid=reviewid;
        setProduct(product);
    }

    //product에 값 넣어주기
    private void setProduct(Product product){
        if(product == null){
            throw new IllegalArgumentException("no product");
        }
        this.product = product;
    }



}
