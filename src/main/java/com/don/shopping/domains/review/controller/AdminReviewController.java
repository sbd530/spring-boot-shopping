package com.don.shopping.domains.review.controller;

import com.don.shopping.domains.review.query.ReviewDao;
import com.don.shopping.domains.review.service.AdminReviewDto;
import com.don.shopping.domains.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AdminReviewController {

    private final ReviewDao reviewDao;
    private final ReviewService reviewService;

    //리뷰를 꺼내는 메소드
    @GetMapping("/dashboard/reviews")
    public List<AdminReviewDto> getReivews(){
        return reviewService
                .findAllReviews()
                .stream()
                .map(AdminReviewDto::new)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/dashboard/reviews/{reviewId}")
    public ResponseEntity deleteReview(@PathVariable Long reviewId){
        reviewDao.deleteReviewOne(reviewId);
        return ResponseEntity.ok().build();


    }


}
