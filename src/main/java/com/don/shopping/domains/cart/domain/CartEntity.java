package com.don.shopping.domains.cart.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "cart")
@Getter
@NoArgsConstructor
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    // UserEntity 의 id 를 참조합니다.
    private Long userId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    private List<CartLineEntity> cartLineList = new ArrayList<>();

    public CartEntity(Long userId) {
        this.userId = userId;
    }

    // 상품을 카트에 추가
    public void addCartLine(CartLineEntity newCartLine) {
        Long newProductId = newCartLine.getProduct().getId();

        for (int i = 0; i < this.cartLineList.size(); i++) {
            CartLineEntity cl = this.cartLineList.get(i);
            if (cl.getProduct().getId() == newProductId) {
                int newOrderAmount = cl.getOrderAmount() + newCartLine.getOrderAmount();
                newCartLine.setOrderAmount(newOrderAmount);
                this.cartLineList.set(i, newCartLine);
                return;
            }
        }
        this.cartLineList.add(newCartLine);
    }

    // 카트라인을 제거
    public void removeCartLine(Long productId) {
        for (int i = 0; i < this.cartLineList.size(); i++) {
            if (this.cartLineList.get(i).getProduct().getId() == productId) {
                this.cartLineList.remove(i);
            }
        }

    }

}
