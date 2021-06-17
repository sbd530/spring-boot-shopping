package com.don.shopping.domains.review.controller;

import com.don.shopping.domains.review.domain.ReviewEntity;
import com.don.shopping.domains.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    //전체조회
    @GetMapping("/reviews")
    public String listProductViewGet(Model model) {
        List<ReviewEntity> reviewEntityList = reviewService.findAllReviews();
        model.addAttribute("reviewlists",reviewEntityList);
        return "review/reviewlist";
    }

    //개별조회
    @GetMapping("/reviews/{reviewId}")  //
    public String review(){
        log.info("review Controller");
        return "review/review";

    }

    //리뷰등록페이지
    @GetMapping("/reviews/add")
    public String addProductViewGet(Model model) {
        model.addAttribute("test","타임리프 잘나오냐?");
        model.addAttribute("reviewForm", new ReviewForm());

        return "review/addreview";
    }

    @PostMapping("/reviews/add")
    public String addProductViewPost(@Valid ReviewForm reviewForm, BindingResult result) { //ReviewForm클래스에 notEmpty가져옴 @Valid

        if(result.hasErrors())
            return "review/addreview";

        ReviewEntity reviewEntity = new ReviewEntity();
        reviewEntity.setContent(reviewForm.getContent());
        reviewEntity.setRating(reviewForm.getRating());
        reviewEntity.setProductId(reviewForm.getProductId());
        Long reviewId = reviewService.addReview(reviewEntity);
        return "redirect:/reviews";
    }
    //상품 안에 리뷰가 나옴
    @GetMapping("/products/{productId}/reviews")
    public String getReviews(Model model, @PathVariable Long productId){

        List<ReviewEntity> reviewsByProductId = reviewService.findReviewsByProductId(productId);

        model.addAttribute("reviewListByProduct",reviewsByProductId);

        return "review/reviewlist";
    }



}
