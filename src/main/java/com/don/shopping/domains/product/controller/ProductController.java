package com.don.shopping.domains.product.controller;

import com.don.shopping.domains.product.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //전체 조회(목록)
    @GetMapping("/products")
    public String productList() {
        return "customer/products/productList.html";
    }

    //개별 조회
    @GetMapping("/products/{productId}")
    public String productDetail() {
        return "customer/products/product.html";
    }



}
