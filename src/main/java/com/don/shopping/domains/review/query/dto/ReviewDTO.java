package com.don.shopping.domains.review.query.dto;

import com.don.shopping.domains.review.domain.ReviewEntity;
import com.don.shopping.domains.user.domain.UserEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDTO {//리뷰할 내용을 담을 DTO

    private Long reviewid; //리뷰의 id(pk) 나중에 리뷰리스트 뽑을때 필요
    private String reviewproductname; //리뷰할 상품이름
    private UserEntity reviewerid; //리뷰 쓴사람 아이디
    private String reviewcontent; //리뷰할 내용
    private String reviewSaveFileName;//저장할 리뷰 사진의 이름
    private String reviewOriginalFileName;//저장할 리뷰 사진의 원래 이름
    private String starscore; //리뷰의 별점
    private String reviewdate; //리뷰 올린 날짜

    public ReviewDTO(ReviewEntity reviewEntity){
        this.reviewid=reviewEntity.getReviewid();
        this.reviewcontent=reviewEntity.getReviewcontent();
        this.starscore=reviewEntity.getStarscore();
        this.reviewdate=reviewEntity.getReviewdate();
        this.reviewerid=reviewEntity.getReviewerid();
        this.reviewproductname=reviewEntity.getReviewproductname();
        this.reviewOriginalFileName=reviewEntity.getReviewOriginalFileName();
        this.reviewSaveFileName=reviewEntity.getReviewSaveFileName();
    }


}
