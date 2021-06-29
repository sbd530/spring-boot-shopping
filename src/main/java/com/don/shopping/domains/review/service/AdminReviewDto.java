package com.don.shopping.domains.review.service;

import com.don.shopping.domains.review.domain.ReviewEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
public class AdminReviewDto {

    private Long id;
    private Long productId;
    private String productName;
    private String content;
    private int rating;
    private Long userId;
    private String userName;
    private String reviewTime;

    public AdminReviewDto(ReviewEntity review) {
        this.productName = review.getProductName();
        this.id = review.getId();
        this.productId = review.getProductId();
        this.content = review.getContent();
        this.rating = review.getRating();
        this.userId = review.getUserId();
        this.userName = review.getUserName();
        this.reviewTime = review
                .getCreatedDate()
                .format(DateTimeFormatter.ofPattern("yy-MM-dd"));
    }
}
