package com.don.shopping.domains.review.query;

import com.don.shopping.domains.review.domain.ReviewEntity;

import java.util.List;

public interface ReviewDao {

    ReviewEntity findOne(Long reviewid); //reviewid로 하나면 값 꺼내기
    List<ReviewEntity> findReviewsByProductId(Long productId); //productId에 있는 모든 리뷰 가져오기 //민감한정보는 dto로
    void deleteReviewOne(Long reviewid);
    Integer ratingSum(Long productId);
    Integer ratingCount(Long productId);
    Double ratingAve(Long productId);

}
