package com.don.shopping.domains.product.query.dao;

import com.don.shopping.domains.product.domain.ProductEntity;
import com.don.shopping.domains.product.query.dto.UpdateProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductDao {

    void updateProductById(Long id, UpdateProductDto updateProductDto);
    List<ProductEntity> findByKeyword(String keyword);
    Long countOutOfStock();
    void updateStock(Long productId, Integer newStock);
}
