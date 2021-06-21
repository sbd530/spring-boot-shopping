package com.don.shopping.domains.cart.domain;

import com.don.shopping.domains.product.domain.ProductEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "cart_line")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartLineEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartLineId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    private Integer orderAmount;

    @Builder
    public CartLineEntity(ProductEntity product, Integer orderAmount) {
        this.product = product;
        this.orderAmount = orderAmount;
    }

    public void setOrderAmount(int newOrderAmount) {
        this.orderAmount = newOrderAmount;
    }
}
