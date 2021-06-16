package com.don.shopping.domains.product.controller;

import com.don.shopping.domains.product.domain.ProductEntity;
import com.don.shopping.domains.product.query.dto.ProductImageResponseDto;
import com.don.shopping.domains.product.query.dto.AdminProductListResponseDto;
import com.don.shopping.domains.product.service.ProductImageService;
import com.don.shopping.domains.product.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {

    private final ProductService productService;
    private final ProductImageService productImageService;

    public ProductController(ProductService productService, ProductImageService productImageService) {
        this.productService = productService;
        this.productImageService = productImageService;
    }

    //개별 조회
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

    /*//전체 조회(목록)
    @GetMapping("/products")
    public String productList() {
        return "customer/products/productList.html";
    }

    //개별 조회
    @GetMapping("/products/{productId}")
    public String productDetail() {
        return "customer/products/Updateproduct.html";
    }*/
}
