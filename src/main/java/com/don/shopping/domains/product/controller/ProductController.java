package com.don.shopping.domains.product.controller;

import com.don.shopping.domains.product.domain.ProductEntity;
import com.don.shopping.domains.product.service.*;
import com.don.shopping.domains.question.controller.QuestionForm;
import com.don.shopping.domains.question.domain.QuestionEntity;
import com.don.shopping.domains.question.service.QuestionService;
import com.don.shopping.domains.review.controller.ReviewForm;
import com.don.shopping.domains.review.domain.ReviewEntity;
import com.don.shopping.domains.review.service.ReviewService;
import com.don.shopping.util.AuthenticationConverter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductImageService productImageService;
    private final ReviewService reviewService;
    private final QuestionService questionService;
    private final AuthenticationConverter ac;

    /*//개별 조회
    @GetMapping("/products/{id}")
    public String findById(@PathVariable Long id, Model model) {

        //상품id로 해당 상품 첨부파일 전체 조회
        List<ProductImageResponseDto> productImageResponseDtoList =
                productImageService.findAllByProduct(id);
        //상품 첨부파일 id 담을 List객체 생성
        List<Long> productimageId = new ArrayList<>();
        //각 첨부파일 id추가
        for(ProductImageResponseDto productImageResponseDto : productImageResponseDtoList) {
            productimageId.add(productImageResponseDto.getFileId());
        }

        ProductEntity product = productService.searchById(id);

        model.addAttribute("product",productService.findOne(id,productimageId));

        return "customer/products/product11.html";
    }*/

    //개별 조회
    @GetMapping("/products/{id}")
    public String findById(@PathVariable Long id, Model model) {

        ProductResponseDto productResponseDto = productService.getProductById(id);
        model.addAttribute("dto",productResponseDto);

        List<ReviewEntity> reviewsByProductId = reviewService.findReviewsByProductId(id); //+무룡파트 해당 product에 모든 Review 출력
        model.addAttribute("reviewForm", new ReviewForm()); //+무룡파트 reviewform형식을 product.html에 뿌려줌
        model.addAttribute("reviewListByProduct",reviewsByProductId); //+무룡파트 해당 product에 모든 Review 출력
        model.addAttribute("id",id);
        List<QuestionEntity> questionEntityList = questionService.findQuestionsByProductId(id);
        model.addAttribute("questionList",questionEntityList); //+무룡파트 해당상품에 대한 질문 출력
        model.addAttribute("questionForm", new QuestionForm()); //+무룡파트 해당 상품에 대한 질문추가 form

        model.addAttribute("ratingCount",reviewService.ratingCount(id)); //+무룡파트 해당 상품에 대한 별점의 개수
        model.addAttribute("ratingAve",reviewService.ratingAve(id));//+무룡파트 해당 상품에 대한 별점의 평균

        return "customer/products/product.html";
    }

    //개별 조회(리뷰,후기 작성 후)
    @PostMapping("/products/{id}")
    public String addProductReviewPost(Model model, @PathVariable Long id, ReviewForm reviewForm, Authentication authentication, QuestionForm questionForm) {

        if(reviewForm.getReviewContent()!=null && reviewForm.getReviewContent()!="") {
            Long userId = ac.getUserFromAuthentication(authentication).getId();
            ReviewEntity reviewEntity = new ReviewEntity();//리뷰 엔티티생성
            reviewEntity.setUserName(reviewService.getUserName(userId));
            reviewEntity.setContent(reviewForm.getReviewContent());
            reviewEntity.setRating(reviewForm.getRating());
            reviewEntity.setProductId(id);
            reviewEntity.setUserId(userId);
            Long reviewId = reviewService.addReview(reviewEntity);

        }
        if(questionForm.getQuestionContent()!=null&&questionForm.getQuestionContent()!="") {
            Long userId = ac.getUserFromAuthentication(authentication).getId();// 질문엔티티생성
            QuestionEntity questionEntity = new QuestionEntity();
            questionEntity.setUserName(questionService.getUserName(userId));
            questionEntity.setProductName(questionService.getProductName(id));
            questionEntity.setContent(questionForm.getQuestionContent());
            questionEntity.setProductId(id);
            questionEntity.setUserId(userId);
            Long questionId = questionService.addQuestion(questionEntity);
        }
        ProductResponseDto productResponseDto = productService.getProductById(id);
        model.addAttribute("dto",productResponseDto);

        List<ReviewEntity> reviewsByProductId = reviewService.findReviewsByProductId(id); //+무룡파트 해당 product에 모든 Review 출력
        model.addAttribute("id",id);//+무룡파트 post방식으로 올때 id
        model.addAttribute("reviewListByProduct",reviewsByProductId); //+무룡파트 해당 product에 모든 Review 출력

        return "redirect:/products/" + id;
    }

    //전체 조회(목록)
    @GetMapping("/products")
    public String searchAll(Model model, Pageable pageable) {
        model.addAttribute("productListDtoList",productService.searchAllDesc(pageable));
        return "customer/products/productList.html";
    }

    //상품조회(키워드기준)
    @GetMapping("/products/search")
    @ResponseBody
    public List<ProductListResponseDto> getByKeyword(@RequestParam("keyword") String keyword) {
        List<ProductEntity> productEntityList = productService.findByKeyword(keyword);
        List<ProductListResponseDto> responseDtoList = new ArrayList<>();

        for(ProductEntity productEntity : productEntityList) {
            ProductListResponseDto productListResponseDto = new ProductListResponseDto(productEntity);
            responseDtoList.add(productListResponseDto);
        }
        return responseDtoList;
    }
}
