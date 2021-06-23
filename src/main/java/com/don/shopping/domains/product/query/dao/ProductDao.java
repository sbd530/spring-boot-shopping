package com.don.shopping.domains.product.query.dao;

import com.don.shopping.domains.product.domain.ProductEntity;
import com.don.shopping.domains.product.query.dto.UpdateProductDto;

import java.util.List;

public interface ProductDao {

    void updateProductById(Long id, UpdateProductDto updateProductDto);
    List<ProductEntity> findByKeyword(String keyword);
    Long countOutOfStock();

}
