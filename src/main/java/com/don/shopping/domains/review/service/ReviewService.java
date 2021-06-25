package com.don.shopping.domains.review.service;


import com.don.shopping.domains.review.domain.ReviewEntity;
import com.don.shopping.domains.review.domain.ReviewRepository;
import com.don.shopping.domains.review.infra.ReviewDaoImpl;
import com.don.shopping.domains.review.query.ReviewDao;
import com.don.shopping.domains.user.domain.UserEntity;
import com.don.shopping.domains.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewDao reviewDao;
    private final UserRepository userRepository;

    //리뷰 등록
    @Transactional
    public Long addReview(ReviewEntity reviewEntity){
        return reviewRepository.save(reviewEntity).getId();
    }

    //리뷰 전체 조회
    @Transactional(readOnly = true)
    public List<ReviewEntity> findAllReviews(){
        return reviewRepository.findAll();
    }

    //리뷰 하나 조회(수정창)
    @Transactional(readOnly = true)
    public ReviewEntity findOneReview(Long reviewId){
        return reviewRepository.findById(reviewId).orElseThrow(()
            -> new IllegalArgumentException("해당 상품이 없습니다."));
    }

    //해당 상품의 모든 리뷰
    @Transactional(readOnly = true)
    public List<ReviewEntity> findReviewsByProductId(Long productId){
        return reviewDao.findReviewsByProductId(productId);
    }

    //해당 상품의 리뷰의 총 별점합
    @Transactional(readOnly = true)
    public int ratingSum(Long productId){
        return reviewDao.ratingSum(productId);
    }
    //해당 상품의 리뷰의 총 개수
    @Transactional(readOnly = true)
    public int ratingCount(Long productId){
        return reviewDao.ratingCount(productId);
    }
    //해당 상품의 리뷰의 별점 평균
    @Transactional(readOnly = true)
    public Double ratingAve(Long productId){
        return reviewDao.ratingAve(productId);
    }

    @Transactional
    public String getUserName(Long userId){
        UserEntity userEntity = userRepository.getOne(userId);
        return userEntity.getName();
    }

}
