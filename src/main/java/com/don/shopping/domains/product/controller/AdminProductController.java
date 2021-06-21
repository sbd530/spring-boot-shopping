package com.don.shopping.domains.product.controller;

import com.don.shopping.domains.product.domain.ProductEntity;
import com.don.shopping.domains.product.domain.ProductImageVO;
import com.don.shopping.domains.product.service.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/dashboard")
public class AdminProductController {

    private final ProductService productService;
    private final ProductImageService productImageService;
    //private final ProductImageHandler productImageHandler;

    //개별 조회
    @GetMapping("/products/{id}")
    public String getAdminProductPage(@PathVariable Long id, Model model) {

        ProductResponseDto productResponseDto = productService.getProductById(id);
        model.addAttribute("dto",productResponseDto);

        return "dashboard/products/updateProduct.html";
    }

    //전체 조회(목록)
    @GetMapping("/products")
    @ResponseBody
    public List<AdminProductListResponseDto> getAdminProductListPage(Model model) {

        //상품 전체 조회
        List<ProductEntity> productEntityList = productService.searchAllDesc();
        //반환할 List<ProductListResponseDto> 생성
        List<AdminProductListResponseDto> adminproductListResponseDtoList = new ArrayList<>();

        for(ProductEntity product : productEntityList) {
            //전체 조회하여 획득한 각 상품 객체를 이용하여 ProductListResponseDto 생성
            AdminProductListResponseDto productListResponseDto = new AdminProductListResponseDto(product);
            adminproductListResponseDtoList.add(productListResponseDto);
        }

//        model.addAttribute("productListDtoList", adminproductListResponseDtoList);

        return adminproductListResponseDtoList;
    }

    //상품등록페이지
    @GetMapping("/products/add")
    public String getAddProductPage() {
        return "dashboard/products/addProduct.html";
    }

    //상품등록
    @PostMapping("/products/add")
    //@ResponseStatus(HttpStatus.CREATED)
    public String addProduct(ProductImageVO productImageVO) throws Exception {

        ProductRequestDto productRequestDto =
                ProductRequestDto.builder()
                        .productName(productImageVO.getProductName())
                        .productInfo(productImageVO.getProductInfo())
                        .rprice(productImageVO.getRprice())
                        .dprice(productImageVO.getDprice())
                        .stock(productImageVO.getStock())
                        .build();

        Long productId= productService.add(productRequestDto, productImageVO.getFiles());

        return "redirect:/dashboard/products/";
    }

    //상품수정
    @PutMapping("/products/{id}")
    public String updateProduct(@PathVariable Long id, ProductImageVO productImageVO) throws Exception {

        productService.updateProduct(id,productImageVO);

        return "redirect:/dashboard/products/" + id;
    }

    //상품삭제
    @DeleteMapping("/products/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return "redirect:/dashboard/products";
    }

    //상품조회(키워드기준)
    @GetMapping("/products/search")
    public String findByKeyword(@RequestParam("keyword") String keyword, Model model) {
        List<ProductEntity> productEntityList = productService.findByKeyword(keyword);
        List<AdminProductListResponseDto> responseDtoList = new ArrayList<>();

        for(ProductEntity productEntity : productEntityList) {
            AdminProductListResponseDto adminProductListResponseDto = new AdminProductListResponseDto(productEntity);
            responseDtoList.add(adminProductListResponseDto);
        }

        model.addAttribute("responseDtoList",responseDtoList);

        return "dashboard/products/productList.html";
    }




    @Data
    static class CreateUpdateProductResponseDto {
        private Long id;

        public CreateUpdateProductResponseDto(Long id) {
            this.id = id;
        }
    }



}
