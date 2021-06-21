package com.don.shopping.domains.product.infra;

import com.don.shopping.domains.product.domain.ProductEntity;
import com.don.shopping.domains.product.domain.QProductEntity;
import com.don.shopping.domains.product.query.dao.ProductDao;
import com.don.shopping.domains.product.query.dto.UpdateProductDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {

    private final JPAQueryFactory query;
    private final QProductEntity product = QProductEntity.productEntity;


    public ProductDaoImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public void updateProductById(Long id, UpdateProductDto updateProductDto) {

        query.update(product)
                .where(product.id.eq(id))
                .set(product.productName, updateProductDto.getProductName())
                .set(product.productInfo, updateProductDto.getProductInfo())
                .set(product.rprice, updateProductDto.getRprice())
                .set(product.dprice, updateProductDto.getDprice())
                .set(product.stock, updateProductDto.getStock())
                .execute();
    }

    @Override
    public List<ProductEntity> findByKeyword(String keyword) {
        List<ProductEntity> productEntityList = query
                .selectFrom(product)
                .where(product.productName.contains(keyword).or(product.productInfo.contains(keyword)))
                .fetch();
        return productEntityList;
    }

}
