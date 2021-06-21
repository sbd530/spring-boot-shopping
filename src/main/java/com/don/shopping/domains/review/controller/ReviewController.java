package com.don.shopping.domains.review.controller;

import com.don.shopping.domains.review.domain.ReviewEntity;
import com.don.shopping.domains.review.query.ReviewDao;
import com.don.shopping.domains.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewDao reviewDao;

    //전체조회
    @GetMapping("/reviews")
    @ResponseBody
    @CrossOrigin("*")
    public List<ReviewEntity> listProductViewGet(Model model) {
        List<ReviewEntity> reviewEntityList = reviewService.findAllReviews();
        //model.addAttribute("reviewlists",reviewEntityList);
        return reviewEntityList;
    }
    //개별조회
    //log.info("review Controller");



    //리뷰 등록
    @GetMapping("/reviews/add")
    public String addProductViewGet(Model model) {
        model.addAttribute("test","타임리프 잘나오냐?");
        model.addAttribute("reviewForm", new ReviewForm());

        return "dashboard/review/addreview";
    }
    //리뷰 등록
    @PostMapping("/reviews/add")
    public String addProductViewPost(@Valid ReviewForm reviewForm, BindingResult result) { //ReviewForm클래스에 notEmpty가져옴 @Valid

        if(result.hasErrors())
            return "dashboard/review/addreview";

        ReviewEntity reviewEntity = new ReviewEntity();
        reviewEntity.setContent(reviewForm.getReviewContent());
        reviewEntity.setRating(reviewForm.getRating());
        reviewEntity.setProductId(reviewForm.getReviewProductId());

        Long reviewId = reviewService.addReview(reviewEntity);
        return "redirect:/reviews";
    }
    //해당 상품의 리뷰
    @GetMapping("/products/{productId}/reviews")
    public String getReviews(Model model, @PathVariable Long productId){

        List<ReviewEntity> reviewsByProductId = reviewService.findReviewsByProductId(productId);

        model.addAttribute("reviewListByProduct",reviewsByProductId);

        return "dashboard/review/reviewlist";
    }

    //개별 수정 update
    @GetMapping("/reviews/{reviewId}/edit")  //
    public String updateReviewForm(@PathVariable("reviewId") Long reviewId,Model model){
        ReviewEntity reviewEntity = reviewDao.findOne(reviewId);
        ReviewForm form = new ReviewForm();
        form.setReviewContent(reviewEntity.getContent());
        form.setRating(reviewEntity.getRating());
        form.setReviewProductId(reviewEntity.getProductId());
        model.addAttribute("reviewForm",form);
        model.addAttribute("id",reviewId);
        return "dashboard/review/reviewupdate";
    }
    //개별 수정 update
    @PostMapping("/reviews/{reviewId}/edit")  //
    public String updateReview(@PathVariable("reviewId") Long reviewId,@ModelAttribute("reviewForm") ReviewForm reviewForm){
        ReviewEntity reviewEntity = new ReviewEntity();
        reviewEntity.setId(reviewId);
        reviewEntity.setProductId(reviewForm.getReviewProductId());
        reviewEntity.setContent(reviewForm.getReviewContent());
        reviewEntity.setRating(reviewForm.getRating());

        reviewService.addReview(reviewEntity);
        return "redirect:/reviews";
    }

    //개별 삭제 delete
    @GetMapping("/reviews/{reviewId}/delete")
    public String deleteReview(@PathVariable("reviewId") Long reviewId){
        reviewDao.deleteReviewOne(reviewId);
        return "redirect:/reviews";
    }



}
