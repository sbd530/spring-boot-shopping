package com.don.shopping.domains.product.controller;

import com.don.shopping.domains.product.domain.ProductEntity;
import com.don.shopping.domains.product.service.*;
import com.don.shopping.domains.question.controller.QuestionForm;
import com.don.shopping.domains.question.domain.QuestionEntity;
import com.don.shopping.domains.question.service.QuestionResponseDto;
import com.don.shopping.domains.question.service.QuestionService;
import com.don.shopping.domains.review.controller.ReviewForm;
import com.don.shopping.domains.review.domain.ReviewEntity;
import com.don.shopping.domains.review.service.ReviewService;
import com.don.shopping.domains.user.domain.UserEntity;
import com.don.shopping.util.AuthenticationConverter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ReviewService reviewService;
    private final QuestionService questionService;
    private final AuthenticationConverter ac;

    //개별 조회
    @GetMapping("/products/{productId}")
    public String findById(@PathVariable Long productId, Model model) {

        ProductResponseDto productResponseDto = productService.getProductById(productId);
        model.addAttribute("dto",productResponseDto);

        List<ReviewEntity> reviewsByProductId = reviewService.findReviewsByProductId(productId); //+무룡파트 해당 product에 모든 Review 출력
        model.addAttribute("reviewForm", new ReviewForm()); //+무룡파트 reviewform형식을 product.html에 뿌려줌
        model.addAttribute("reviewListByProduct", reviewsByProductId); //+무룡파트 해당 product에 모든 Review 출력
        model.addAttribute("id", productId);

        List<QuestionResponseDto> questionList = questionService.findQuestionsByProductId(productId);
        model.addAttribute("questionList",questionList); //+무룡파트 해당상품에 대한 질문 출력
        model.addAttribute("questionForm", new QuestionForm()); //+무룡파트 해당 상품에 대한 질문추가 form

        model.addAttribute("ratingCount",reviewService.ratingCount(productId)); //+무룡파트 해당 상품에 대한 별점의 개수
        model.addAttribute("ratingAve",reviewService.ratingAve(productId));//+무룡파트 해당 상품에 대한 별점의 평균

        return "customer/products/product";
    }

    // 리뷰 등록
    @PostMapping("/products/{productId}/reviews")
    public String addProductReview(Model model, @PathVariable Long productId,
                                   @Valid ReviewForm reviewForm, Authentication authentication) {

        UserEntity user = ac.getUserFromAuthentication(authentication);
        ReviewEntity reviewEntity = ReviewEntity.builder()
                    .userId(user.getId())
                    .userName(user.getName())
                    .content(reviewForm.getReviewContent())
                    .rating(reviewForm.getRating())
                    .productId(productId)
                    .build();
        Long reviewId = reviewService.addReview(reviewEntity);
        return "redirect:/products/" + productId;
    }

    // 질문 등록
    @PostMapping("/products/{productId}/questions")
    public String addProductQuestion(@PathVariable Long productId,
                                       @Valid QuestionForm questionForm, Authentication authentication) {
        Long userId = ac.getUserFromAuthentication(authentication).getId();
        QuestionEntity questionEntity = QuestionEntity.builder()
                .userId(userId)
                .content(questionForm.getQuestionContent())
                .productId(productId)
                .answer("미답변")
                .build();
        Long questionId = questionService.addQuestion(questionEntity);
        return "redirect:/products/" + productId;
    }



    //전체 조회(목록)
    @GetMapping("/products")
    public String searchAll(Model model, Pageable pageable) {
        model.addAttribute("productListDtoList",productService.searchAllDesc(pageable));//어드민
        return "customer/products/productList.html";
    }

    //상품조회(키워드기준)
    @GetMapping("/products/search")
    public ModelAndView getByKeyword(@RequestParam(value = "keyword", required = false) String keyword) {

        ModelAndView mav = new ModelAndView();
        List<ProductEntity> productEntityList = productService.findByKeyword(keyword);
        List<AdminProductListResponseDto> responseDtoList = new ArrayList<>();

        for(ProductEntity productEntity : productEntityList) {
            AdminProductListResponseDto productListResponseDto = new AdminProductListResponseDto(productEntity);
            responseDtoList.add(productListResponseDto);
        }
        mav.addObject("productListDtoList",responseDtoList);
        mav.setViewName("customer/products/productList.html");
        return mav;
    }
}
