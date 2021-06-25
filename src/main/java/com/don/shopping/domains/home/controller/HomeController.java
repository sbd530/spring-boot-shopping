package com.don.shopping.domains.home.controller;

import com.don.shopping.domains.product.domain.ProductEntity;
import com.don.shopping.domains.product.domain.ProductRepository;
import com.don.shopping.domains.product.service.AdminProductListResponseDto;
import com.don.shopping.domains.product.service.ProductService;
import com.don.shopping.util.AuthenticationConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ProductRepository productRepository;
    private final ProductService productService;
    private final AuthenticationConverter authenticationConverter;

    //메인페이지
    /*@GetMapping("/home")
    public String getHomePage(Model model, Pageable pageable) {
//        Long userId = authenticationConverter.getUserFromAuthentication(authentication).getId();
        model.addAttribute("productListDtoList",productService.searchAllDesc(pageable));
        return "customer/main/main";
    }*/

    /*@GetMapping("/home")
    public ModelAndView getHomePage(Pageable pageable) {
        ModelAndView mav = new ModelAndView();
        PageRequest pageRequest = PageRequest.of(0,3);
        Page<ProductEntity> products = productRepository.findAll(pageRequest);
        mav.addObject("productListDtoList",products);
        mav.setViewName("customer/main/main");
        return mav;
    }*/

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
