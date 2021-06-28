package com.don.shopping.domains.product.service;

import com.don.shopping.domains.product.domain.*;
import com.don.shopping.domains.product.query.dao.ProductDao;
import com.don.shopping.domains.product.query.dao.ProductImageDao;
import com.don.shopping.domains.product.query.dto.UpdateProductDto;
import com.don.shopping.domains.product.query.dto.UpdateProductImageDto;
import com.don.shopping.domains.product.util.ProductImageHandler;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final ProductImageHandler productImageHandler;
    private final ProductDao productDao;
    private final ProductImageDao productImageDao;

    @PersistenceContext
    private EntityManager em;

    //상품등록
    public Long add(ProductRequestDto productRequestDto, List<MultipartFile> files) throws Exception {
        //파일 처리를 위한 ProductEntity 객체 생성
        ProductEntity productEntity = new ProductEntity(
                productRequestDto.getProductName(),
                productRequestDto.getProductInfo(),
                productRequestDto.getRprice(),
                productRequestDto.getDprice(),
                productRequestDto.getStock()
        );

        List<ProductImageEntity> productImageEntityList = productImageHandler.parseFileInfo(productEntity, files);

        //파일이 존재할 때만 처리
        if(!CollectionUtils.isEmpty(productImageEntityList)) {
            for(ProductImageEntity productImageEntity : productImageEntityList)
                //파일을 DB에 저장
                productEntity.addImage(productImageRepository.save(productImageEntity));
        }
        return productRepository.save(productEntity).getId();
    }

    //상품수정
    public void update(Long id, ProductRequestDto productRequestDto, List<MultipartFile> files) throws Exception {

        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 존재하지 않습니다"));

        List<ProductImageEntity> productImageEntityList = productImageHandler.parseFileInfo(productEntity, files);

        if(!productImageEntityList.isEmpty()) {
            for(ProductImageEntity productImageEntity : productImageEntityList) {
                productImageRepository.save(productImageEntity);
            }
        }

        productEntity.update(productRequestDto.getProductName(), productRequestDto.getProductInfo(),
                productRequestDto.getRprice(), productRequestDto.getDprice(), productRequestDto.getStock());
    }

    //상품삭제
    public void delete(Long id) {
       ProductEntity productEntity = productRepository.findById(id)
               .orElseThrow(()->new IllegalArgumentException("해당 상품이 존재하지 않습니다."));
       List<ProductImageEntity> productImages = productEntity.getProductImages();
       productRepository.delete(productEntity);
       //물리적파일삭제
        for (ProductImageEntity productImage : productImages) {
            String saveFileName = productImage.getSaveFileName();
            productImageHandler.doFileDelete(saveFileName);
        }
    }

    //상품 개별조회(정보 + 파일)
    @Transactional(readOnly = true)
    public ProductResponseDto getProductById(Long id){
       ProductEntity productEntity = productRepository.findById(id).orElseThrow(()
               -> new IllegalArgumentException("해당 상품이 존재하지 않습니다."));

       return new ProductResponseDto(productEntity);
    }

    //상품 개별조회(정보)
    @Transactional(readOnly = true)
    public ProductEntity searchById(Long id) {
        return productRepository.findById(id).orElseThrow(()
            -> new IllegalArgumentException("해당 상품이 존재하지 않습니다."));
    }

    //상품 전체조회
    @Transactional(readOnly = true)
    @Cacheable(value = "adminProductList", key = "#pageable")
    public List<AdminProductListResponseDto> searchAllDesc(Pageable pageable) {
        List<ProductEntity> productEntityList = productRepository.findAll();
        return productEntityList.stream()
                .map(AdminProductListResponseDto::new)
                .collect(Collectors.toList());
    }

    // 상품 개별 조회
    @Transactional(readOnly = true)
    public AdminProductListResponseDto findOneAdminProduct(Long productId) {
        ProductEntity productEntity =
                productRepository.findById(productId)
                        .orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));
        return new AdminProductListResponseDto(productEntity);
    }

    //키워드별 상품 전체조회
    @Transactional(readOnly = true)
    public List<ProductEntity> findByKeyword(String keyword) {
        return productDao.findByKeyword(keyword);
    }

    //상품수정
    public void updateProduct(Long productId, ProductImageVO productImageVO) throws Exception {

        //db업데이트
        Optional<ProductEntity> productOptional = productRepository.findById(productId);
        ProductEntity product = productOptional
                .orElseThrow(()-> new IllegalStateException("해당 상품이 없습니다."));

        List<ProductImageEntity> productImages = product.getProductImages();
        productDao.updateProductById(productId,new UpdateProductDto(productImageVO));

        //물리적파일삭제
        for(int i=0;i<4;i++) {
            if(productImageVO.getFiles().get(i) != null) {
                String saveFileName = productImages.get(i).getSaveFileName();
                productImageHandler.doFileDelete(saveFileName);
            }
        }

        //파일추가
        List<ProductImageEntity> productImageEntityList
                = productImageHandler.parseFileInfo(product, productImageVO.getFiles());

        for (ProductImageEntity productImageEntity : productImageEntityList) {
            product.addImage(productImageRepository.save(productImageEntity));//파일을 db에 저장
            //productImageDao.updateProductImageByProductId(new UpdateProductImageDto(productImageEntity));//db상품이미지업데이트
        }

        List<Long> imageIdList = product.getProductImages().stream()
                .map(image -> image.getId())
                .collect(Collectors.toList());
        for(int i=0; i<4; i++) {
            if(imageIdList.get(i)!=0) {
                productImageDao.updateProductImageByProductId(imageIdList.get(i),
                        new UpdateProductImageDto(productImageEntityList.get(i)));
            }
        }
    }

    public void updateStock(Long productId, Integer newStock) {
        productDao.updateStock(productId, newStock);
    }
}
