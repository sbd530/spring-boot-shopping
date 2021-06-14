package com.don.shopping.domains.review.domain;

import com.don.shopping.domains.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;



@Entity
@Getter
@Setter
@NoArgsConstructor
public class ReviewEntity { //테이블이라고 생각하면댐 DTO랑 그런데 얘는 직접 테이블과 연관댐

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewid; //리뷰의 id(pk) 나중에 리뷰리스트 뽑을때 필요

    private String reviewproductname; //리뷰할 상품이름
    //private String reviewerid; //리뷰 쓴사람 이름
    private String reviewcontent; //리뷰할 내용
    private String reviewSaveFileName;//저장할 리뷰 사진의 이름
    private String reviewOriginalFileName;//저장할 리뷰 사진의 원래 이름
    private String starscore; //리뷰의 별점
    private String reviewdate; //리뷰 올린 날짜

    @ManyToOne//1명이 여러개의 리뷰를 달수 있기때문에 //맞나?
    @JoinColumn(name="id") //미지정
    private UserEntity reviewerid; //리뷰 쓴사람 id

    /*@ManyToOne//1명이 여러개의 리뷰를 달수 있기때문에 //맞나?
    @JoinColumn(name="productname") //미지정
    private ProductEntity reviewproductname; //어떤 상품의 리뷰?
*/
    @Builder //reviewproductname 미추가
    public ReviewEntity(Long reviewid, String reviewproductname, String reviewcontent, String reviewSaveFileName, String reviewOriginalFileName, String starscore, String reviewdate, UserEntity reviewerid) {
        this.reviewid = reviewid;
        this.reviewproductname = reviewproductname;
        this.reviewcontent = reviewcontent;
        this.reviewSaveFileName = reviewSaveFileName;
        this.reviewOriginalFileName = reviewOriginalFileName;
        this.starscore = starscore;
        this.reviewdate = reviewdate;
        this.reviewerid = reviewerid;
    }


    /*
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
    }*/



}
