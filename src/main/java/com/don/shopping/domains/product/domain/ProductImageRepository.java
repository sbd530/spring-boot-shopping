package com.don.shopping.domains.product.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImageEntity, Long> {
    List<ProductImageEntity> findAllByProductId(Long productId);
}
