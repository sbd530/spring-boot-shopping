package com.don.shopping.domains.review.presentation;


import com.don.shopping.domains.review.query.dto.ReviewDTO;
import com.don.shopping.domains.review.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService){
        this.reviewService=reviewService;
    }

    @GetMapping("/reviews")//메뉴바에서 Review누르면 나옴GET방식
    public String getReview(){
        return "review/review";//요기 html로 보냄 근데 리스트를 보내 줘야 하니깐
    }

    @GetMapping("/reviews/insert")
    public String getReviewGet(Model model){
        List<ReviewDTO> reviewDTOList= reviewService.searchAllDesc();
        model.addAttribute("reviewList",reviewDTOList);
        return "/review";
    }

    @PostMapping("/reviews/insert")
    public String getReviewPost(ReviewDTO reviewDTO) throws Exception {

        Long reviewid = reviewService.createReview(reviewDTO);

        return "redirect:/review/" + reviewid;
    }



}
