package com.don.shopping.domains.product.controller;

import com.don.shopping.domains.product.domain.ProductEntity;
import com.don.shopping.domains.product.domain.ProductImageVO;
import com.don.shopping.domains.product.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/dashboard")
public class AdminProductController {

    private final ProductService productService;
    private final ProductImageService productImageService;

    //전체 조회(목록)
    @GetMapping("/products")
    public List<AdminProductListResponseDto> getAdminProductList(Pageable pageable) {
        return productService.searchAllDesc(pageable);
    }

    // 개별 조회
    @GetMapping("/products/{productId}")
    public AdminProductListResponseDto getOneAdminProduct(@PathVariable Long productId) {
        return productService.findOneAdminProduct(productId);
    }

    //상품등록
    @PostMapping("/products/add")
    public ResponseEntity addProduct(ProductImageVO productImageVO) throws Exception {

        ProductRequestDto productRequestDto =
                ProductRequestDto.builder()
                        .productName(productImageVO.getProductName())
                        .productInfo(productImageVO.getProductInfo())
                        .rprice(productImageVO.getRprice())
                        .dprice(productImageVO.getDprice())
                        .stock(productImageVO.getStock())
                        .build();

        Long productId = productService.add(productRequestDto, productImageVO.getFiles());

        if (productId == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    //상품수정
    @PutMapping("/products/{productId}")
    public ResponseEntity updateProduct(@PathVariable Long productId, ProductImageVO productImageVO) throws Exception {
        productService.updateProduct(productId,productImageVO);
        return ResponseEntity.ok().build();
    }

    // 재고수정
    @PatchMapping("/products/{productId}")
    public ResponseEntity updateStock(@PathVariable Long productId, @RequestBody @NotNull Integer newStock) {
        productService.updateStock(productId, newStock);
        return ResponseEntity.ok().build();
    }

    //상품삭제
    @DeleteMapping("/products/{productId}")
    public ResponseEntity deleteProduct(@PathVariable Long productId) {
        productService.delete(productId);
        return ResponseEntity.ok().build();
    }

}
