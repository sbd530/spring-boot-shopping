package com.don.shopping.domains.review.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter @Setter
public class ReviewForm {

    @NotBlank(message="리뷰 내용을 입력해주세요.")
    private String reviewContent;
    @Min(value = 1,message = "별점을 1이상 주세요")
    private int rating;
    private Long reviewProductId;

}
