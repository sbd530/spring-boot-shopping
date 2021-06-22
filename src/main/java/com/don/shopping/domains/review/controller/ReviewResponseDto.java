package com.don.shopping.domains.review.controller;

import com.don.shopping.domains.review.domain.ReviewEntity;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ReviewResponseDto {

    private String productName;
    private LocalDateTime reviewTime;
    private int rating;
    private String userName;
    private int ratingCount;
    private double ratingAve;

    public ReviewResponseDto(String productName, LocalDateTime reviewTime, int rating, String userName, int ratingCount, double ratingAve) {
        this.productName = productName;
        this.reviewTime = reviewTime;
        this.rating = rating;
        this.userName = userName;
        this.ratingCount = ratingCount;
        this.ratingAve = ratingAve;
    }
}
