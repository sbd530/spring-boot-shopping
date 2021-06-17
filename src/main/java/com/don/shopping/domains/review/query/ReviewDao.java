package com.don.shopping.domains.review.query;

import com.don.shopping.domains.review.domain.ReviewEntity;

import java.util.List;

public interface ReviewDao {

    ReviewEntity findOne(Long reviewid);

    List<ReviewEntity> findReviewsByProductId(Long productId); //민감한정보는 dto로

}
