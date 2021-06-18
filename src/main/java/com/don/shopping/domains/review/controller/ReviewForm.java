package com.don.shopping.domains.review.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Getter @Setter
public class ReviewForm {


    @NotEmpty(message="리뷰 내용을 입력해주세요.")
    private String content;
    @Min(value = 1,message = "별점을 1이상 주세요")
    private int rating;
    private Long productId;



}