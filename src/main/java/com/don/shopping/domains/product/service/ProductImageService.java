package com.don.shopping.domains.product.service;

import com.don.shopping.common.exception.NoSuchUsageException;
import com.don.shopping.domains.product.domain.ImageUsage;
import com.don.shopping.domains.product.domain.ProductImageEntity;
import com.don.shopping.domains.product.domain.ProductImageRepository;
import com.don.shopping.domains.product.domain.QProductImageEntity;
import com.don.shopping.domains.product.query.dao.ProductImageDao;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class ProductImageService {
    private final ProductImageRepository productImageRepository;
    private final ProductImageDao productImageDao;

    @PersistenceContext
    private EntityManager em;

    //이미지 저장
    @Transactional
    public Long saveProductImage(ProductImageDto productImageDto) {
        return productImageRepository.save(productImageDto.toEntity()).getId();
    }

    //이미지 삭제
    @Transactional
    public void deleteProductImage(Long id) {
        //ProductImageDto productImageDto;
        productImageRepository.deleteById(id);
    }

    //이미지 개별 조회
    @Transactional(readOnly = true)
    public ProductImageDto findByFileId(Long id) {

        ProductImageEntity productImageEntity = productImageRepository.findById(id).orElseThrow(()
                    -> new IllegalArgumentException("해당 파일이 존재하지 않습니다."));

        ProductImageDto productImageDto = ProductImageDto.builder()
                .originalFileName(productImageEntity.getOriginalFileName())
                .saveFileName(productImageEntity.getSaveFileName())
                .imageUsage(productImageEntity.getImageUsage())
                .fileSize(productImageEntity.getFileSize())
                .build();

        return productImageDto;
    }

    //상품 개별 조회
    @Transactional(readOnly = true)
    public String getFileNameForProductPageByUsage(Long productId, String usage) {

        ImageUsage imageUsage;

        try {
            imageUsage = ImageUsage.valueOf(usage);
        }catch (IllegalArgumentException e) {
            throw new NoSuchUsageException("이미지 용도가 맞지 않습니다.");
        }

        String fileName = productImageDao.findFileNameByProductIdAndUsage(productId,imageUsage);

        return fileName;
    }


    //이미지 전체 조회
    @Transactional(readOnly = true)
    public List<ProductImageResponseDto> findAllByProduct(Long productid) {

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        QProductImageEntity productImageEntity = QProductImageEntity.productImageEntity;

        List<ProductImageEntity> productImageEntityList = queryFactory
                .selectFrom(productImageEntity)
                .where(productImageEntity.product.id.eq(productid))
                .fetch();

        return productImageEntityList.stream()
                .map(ProductImageResponseDto::new)
                .collect(Collectors.toList());
    }
}
