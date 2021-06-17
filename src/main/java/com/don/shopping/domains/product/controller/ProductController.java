package com.don.shopping.domains.product.controller;

import com.don.shopping.domains.product.domain.ProductEntity;
import com.don.shopping.domains.product.service.AdminProductListResponseDto;
import com.don.shopping.domains.product.service.ProductImageResponseDto;
import com.don.shopping.domains.product.service.ProductImageService;
import com.don.shopping.domains.product.service.ProductService;
import com.don.shopping.domains.review.controller.ReviewForm;
import com.don.shopping.domains.review.domain.ReviewEntity;
import com.don.shopping.domains.review.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductImageService productImageService;
    private final ReviewService reviewService;


    //개별 조회 (리뷰작성,후기작성)
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

        List<ReviewEntity> reviewsByProductId = reviewService.findReviewsByProductId(id); //+무룡파트 해당 product에 모든 Review 출력
        model.addAttribute("id",id);//+무룡파트 post방식으로 올때 id
        model.addAttribute("reviewForm", new ReviewForm()); //+무룡파트 reviewform형식을 product.html에 뿌려줌
        model.addAttribute("product",productService.findOne(id,productimageId));
        model.addAttribute("reviewListByProduct",reviewsByProductId); //+무룡파트 해당 product에 모든 Review 출력
        return "customer/products/product.html";
    }

    //개별 조회 (리뷰작성,후기작성)
    @PostMapping("/products/{id}")
    public String addProductViewPost(Model model, @PathVariable Long id,@Valid ReviewForm reviewForm, BindingResult result) { //ReviewForm클래스에 notEmpty가져옴 @Valid

        if(result.hasErrors())
            return "customer/products/product.html";

        ReviewEntity reviewEntity = new ReviewEntity();
        reviewEntity.setContent(reviewForm.getContent());
        reviewEntity.setRating(reviewForm.getRating());
        reviewEntity.setProductId(reviewForm.getProductId());
        Long reviewId = reviewService.addReview(reviewEntity);

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

        List<ReviewEntity> reviewsByProductId = reviewService.findReviewsByProductId(id); //+무룡파트 해당 product에 모든 Review 출력
        model.addAttribute("reviewForm", new ReviewForm()); //+무룡파트 reviewform형식을 product.html에 뿌려줌
        model.addAttribute("product",productService.findOne(id,productimageId));
        model.addAttribute("reviewListByProduct",reviewsByProductId); //+무룡파트 해당 product에 모든 Review 출력

        return "customer/products/product.html";
    }



    //전체 조회(목록)
    @GetMapping("/products")
    public String searchAll(Model model) {

        //상품 전체 조회
        List<ProductEntity> productEntityList = productService.searchAllDesc();
        //반환할 List<ProductListResponseDto> 생성
        List<AdminProductListResponseDto> productListResponseDtoList = new ArrayList<>();

        for(ProductEntity product : productEntityList) {
            //전체 조회하여 획득한 각 상품 객체를 이용하여 ProductListResponseDto 생성
            AdminProductListResponseDto productListResponseDto = new AdminProductListResponseDto(product);
            productListResponseDtoList.add(productListResponseDto);
        }

        model.addAttribute("productListDtoList",productListResponseDtoList);

        return "customer/products/productList.html";
    }



}
