package com.don.shopping.domains.product.query.dao;

import com.don.shopping.domains.product.query.dto.UpdateProductDto;

public interface ProductDao {

    void updateProductById(Long id, UpdateProductDto updateProductDto);

}
