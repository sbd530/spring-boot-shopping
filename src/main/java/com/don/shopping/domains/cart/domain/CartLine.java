package com.don.shopping.domains.cart.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@Getter
public class CartLine {

    private Long cartId;
    private Long productId;
    private Integer orderAmount;

    @Builder
    public CartLine(Long cartId, Long productId, Integer orderAmount) {
        this.cartId = cartId;
        this.productId = productId;
        this.orderAmount = orderAmount;
    }
}
