package com.don.shopping.domains.cart.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long> {

    CartEntity findFirstByUserId(Long userId);
    void deleteByUserId(Long userId);
}
