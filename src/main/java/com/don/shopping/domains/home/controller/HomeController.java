package com.don.shopping.domains.home.controller;

import com.don.shopping.domains.product.domain.ProductEntity;
import com.don.shopping.domains.product.domain.ProductRepository;
import com.don.shopping.domains.product.service.ProductService;
import com.don.shopping.util.AuthenticationConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ProductRepository productRepository;
    private final ProductService productService;
    private final AuthenticationConverter authenticationConverter;

    @GetMapping("/home")
    public String getHomePage(Model model, @PageableDefault(size = 3) Pageable pageable) {
        Page<ProductEntity> products = productRepository.findAll(pageable);
        int startPage = Math.max(1,products.getPageable().getPageNumber() - 4);
        int endPage = Math.min(products.getTotalPages(), products.getPageable().getPageNumber() + 4);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("productListDtoList",products);
        return "customer/main/main";
    }

    @GetMapping("/about")
    public String getAboutPage() {
        return "customer/main/about";
    }
    @GetMapping("/lookbook")
    public String getLookBookPage() {
        return "customer/main/lookbook";
    }

}
