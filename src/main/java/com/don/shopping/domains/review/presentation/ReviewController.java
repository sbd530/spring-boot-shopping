package com.don.shopping.domains.review.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReviewController {


    @GetMapping("/reviews")//메뉴바에서 Review누르면 나옴GET방식
    public String getReview(Model model){

        //리뷰 리스트를 가지고 리턴
        return "reviews";
    }




}
