package com.don.shopping.domains.product.service;

import com.don.shopping.domains.product.domain.*;
import com.don.shopping.domains.product.query.dto.ProductRequestDto;
import com.don.shopping.domains.product.query.dto.ProductResponseDto;
import com.don.shopping.domains.product.util.ProductImageHandler;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final ProductImageHandler productImageHandler;

    @PersistenceContext
    private EntityManager em;

    //상품등록
   @Transactional
    public Long add(ProductRequestDto productRequestDto, List<MultipartFile> files) throws Exception {
        //파일 처리를 위한 ProductEntity 객체 생성
        ProductEntity productEntity = new ProductEntity(
                productRequestDto.getProductname(),
                productRequestDto.getProductinfo(),
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
    @Transactional
    public void update(Long id, ProductRequestDto productRequestDto, List<MultipartFile> files) throws Exception {

        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 존재하지 않습니다"));

        List<ProductImageEntity> productImageEntityList = productImageHandler.parseFileInfo(productEntity, files);

        if(!productImageEntityList.isEmpty()) {
            for(ProductImageEntity productImageEntity : productImageEntityList) {
                productImageRepository.save(productImageEntity);
            }
        }

        productEntity.update(productRequestDto.getProductname(), productRequestDto.getProductinfo(),
                productRequestDto.getRprice(), productRequestDto.getDprice(), productRequestDto.getStock());
    }

    //상품삭제
    @Transactional
    public void delete(Long id) {
       ProductEntity productEntity = productRepository.findById(id)
               .orElseThrow(()->new IllegalArgumentException("해당 상품이 존재하지 않습니다."));

       productRepository.delete(productEntity);
    }

    //상품 개별조회(정보 + 파일)
    @Transactional(readOnly = true)
    public ProductResponseDto findOne(Long id, List<Long> fileId){
       ProductEntity productEntity = productRepository.findById(id).orElseThrow(()
               -> new IllegalArgumentException("해당 상품이 존재하지 않습니다."));

       return new ProductResponseDto(productEntity, fileId);
    }

    //상품 개별조회(정보)
    @Transactional(readOnly = true)
    public ProductEntity searchById(Long id) {
        ProductEntity productEntity = productRepository.findById(id).orElseThrow(()
            -> new IllegalArgumentException("해당 상품이 존재하지 않습니다."));

        return productEntity;
    }

    //상품 전체조회
    @Transactional(readOnly = true)
    public List<ProductEntity> searchAllDesc() {
        return productRepository.findAll();
    }

    //키워드별 상품 전체조회
    @Transactional(readOnly = true)
    public List<ProductEntity> findByKeyword(String keyword) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        QProductEntity productEntity = QProductEntity.productEntity;

        List<ProductEntity> productEntityList = queryFactory
                .selectFrom(productEntity)
                .where(productEntity.productname.contains(keyword).or(productEntity.productinfo.contains(keyword)))
                .fetch();

        return productEntityList;
    }
}
