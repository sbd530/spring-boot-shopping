package com.don.shopping.domains.product.infra;

import com.don.shopping.domains.product.domain.ImageUsage;
import com.don.shopping.domains.product.domain.QProductImageEntity;
import com.don.shopping.domains.product.query.dao.ProductImageDao;
import com.don.shopping.domains.product.query.dto.UpdateProductImageDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class ProductImageDaoImpl implements ProductImageDao {
    private final JPAQueryFactory query;
    private final QProductImageEntity productImage = QProductImageEntity.productImageEntity;

    public ProductImageDaoImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public String findFileNameByProductIdAndUsage(Long productId, ImageUsage usage) {

        String fileName = query
                .select(productImage.saveFileName)
                .from(productImage)
                .where(productImage.product.id.eq(productId).and(productImage.imageUsage.eq(usage)))
                .fetchOne();

        return fileName;
    }

    @Override
    public void updateProductImageByProductId(Long imageId, UpdateProductImageDto updateProductImageDto) {

        query.update(productImage)
                .set(productImage.saveFileName, updateProductImageDto.getSaveFileName())
                .set(productImage.fileSize, updateProductImageDto.getFileSize())
                .where(productImage.id.eq(imageId))
                .execute();

    }
}
