package com.don.shopping.domains.product.controller;

import com.don.shopping.domains.product.domain.ImageUsage;
import com.don.shopping.domains.product.domain.ProductEntity;
import com.don.shopping.domains.product.domain.ProductImageEntity;
import com.don.shopping.domains.product.domain.ProductImageVO;
import com.don.shopping.domains.product.query.dto.*;
import com.don.shopping.domains.product.service.ProductImageService;
import com.don.shopping.domains.product.service.ProductService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
@RequestMapping("/dashboard")
public class AdminProductController {

    private final ProductService productService;
    private final ProductImageService productImageService;

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

        ProductEntity productEntity = productService.searchById(id);

        model.addAttribute("product",productService.findOne(id,productimageId));

        return "dashboard/products/product.html";
    }

    //전체 조회(목록)
    @GetMapping("/products")
    public String searchAll(Model model) {

        //상품 전체 조회
        List<ProductEntity> productEntityList = productService.searchAllDesc();
        //반환할 List<ProductListResponseDto> 생성
        List<ProductListResponseDto> productListResponseDtoList = new ArrayList<>();

        for(ProductEntity productEntity : productEntityList) {
            //전체 조회하여 획득한 각 상품 객체를 이용하여 ProductListResponseDto 생성
            ProductListResponseDto productListResponseDto = new ProductListResponseDto(productEntity);
            productListResponseDtoList.add(productListResponseDto);
        }

        model.addAttribute("productList",productListResponseDtoList);

        return "dashboard/products/productList.html";
    }

    //상품등록페이지
    @GetMapping("/products/add")
    public String addProductView() {
        return "dashboard/products/addProduct.html";
    }

    //상품등록
    @PostMapping("/products/add")
    @ResponseStatus(HttpStatus.CREATED)
    public String add(ProductImageVO productImageVO) throws Exception {

        ProductRequestDto productRequestDto =
                ProductRequestDto.builder()
                        .productname(productImageVO.getProductname())
                        .productinfo(productImageVO.getProductinfo())
                        .rprice(productImageVO.getRprice())
                        .dprice(productImageVO.getDprice())
                        .stock(productImageVO.getStock())
                        .build();

        Long productId= productService.add(productRequestDto, productImageVO.getFiles());

        System.out.println("상품등록성공!!");

        return "redirect:/dashboard/products/" + productId;

    }

    //상품수정
    @PutMapping("/products/{id}")
    public String update(@PathVariable Long id, ProductImageVO productImageVO, Model model) throws Exception {
        //ProductImageVO는 이미지 포함한 상품VO

        ProductRequestDto productRequestDto =
                ProductRequestDto.builder()
                        .productname(productImageVO.getProductname())
                        .productinfo(productImageVO.getProductinfo())
                        .rprice(productImageVO.getRprice())
                        .dprice(productImageVO.getDprice())
                        .stock(productImageVO.getStock())
                        .build();

        model.addAttribute("originalDto",productRequestDto);

        //DB에 저장되어있는 파일 불러오기
        List<ProductImageResponseDto> dbProductImageList = productImageService.findAllByProduct(id);
        //전달되어온 파일들
        List<MultipartFile> multipartFileList = productImageVO.getFiles();
        //새롭게 전달되어온 파일들의 목록을 저장할 List선언
        List<MultipartFile> validFileList = new ArrayList<>();

        if(CollectionUtils.isEmpty(dbProductImageList)) {//DB에 아예 존재x
            if(!CollectionUtils.isEmpty(multipartFileList)) {//전달되어온 파일이 하나라도 존재
                for(MultipartFile multipartFile : multipartFileList) {
                    validFileList.add(multipartFile); //저장할 파일 목록에 추가
                }
            }
        } else {//DB에 한장 이상 존재
            if(CollectionUtils.isEmpty(multipartFileList)) {//전달되어온 파일 x
                for (ProductImageResponseDto dbProductImage : dbProductImageList) {
                    productImageService.deleteProductImage(dbProductImage.getFileId());
                }
            } else {//전달되어온 파일 한장이상 존재
                //DB에 저장되어있는 파일 원본명 목록
                List<String> dbOriginalnameList = new ArrayList<>();

                //DB파일 원본명 추출
                for(ProductImageResponseDto dbProductImage : dbProductImageList) {//db의 파일 원본명 추출
                    //file id로 DB에 저장된 파일 정보 얻기
                    ProductImageDto dbProductImageDto = productImageService.findByFileId(dbProductImage.getFileId());
                    //DB의 파일 원본명 얻기
                    String dbOriginalname = dbProductImageDto.getOriginalfilename();

                    if(!multipartFileList.contains(dbOriginalname)) {//삭제 요청 파일
                        productImageService.deleteProductImage(dbProductImage.getFileId()); //파일 삭제
                    } else {
                        dbOriginalnameList.add(dbOriginalname); //DB에 저장할 파일 목록에 추가
                    }

                    for(MultipartFile multipartFile : multipartFileList) {//전달되어온 파일 하나씩 검사
                        //파일의 원본명 얻기
                        String multipartOriginalname = multipartFile.getOriginalFilename();
                        if(!dbOriginalnameList.contains(multipartOriginalname)) {//DB에 없는 파일이면
                            validFileList.add(multipartFile); //DB에 저장할 파일 목록에 추가
                        }
                    }
                }
            }
        }

        productService.update(id,productRequestDto,validFileList);

        //각각 인자로 상품의 id, 수정할 정보, DB에 저장할 파일 목록 넘겨주기

        return "redirect:/dashboard/products";
    }

    //상품삭제
    @DeleteMapping("/products/{id}")
    public String delete(@PathVariable Long id) {
        productService.delete(id);
        return "redirect:/dashboard/products";
    }


    @Data
    static class CreateUpdateProductResponseDto {
        private Long id;

        public CreateUpdateProductResponseDto(Long id) {
            this.id = id;
        }
    }

}
