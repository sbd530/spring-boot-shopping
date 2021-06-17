package com.don.shopping.domains.product.query.dao;

import com.don.shopping.domains.product.domain.ImageUsage;
import com.don.shopping.domains.product.query.dto.UpdateProductImageDto;

public interface ProductImageDao {

    String findFileNameByProductIdAndUsage(Long id, ImageUsage usage);

    void updateProductImageByProductId(Long imageId, UpdateProductImageDto updateProductImageDto);

}
