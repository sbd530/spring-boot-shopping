package com.don.shopping.domains.cart.domain;

import com.don.shopping.common.exception.StockShortageException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

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

    // 타입파라미터의 Long 은 ProductEntity 의 id
    @ElementCollection
    @CollectionTable(name = "cart_line")
    @MapKeyColumn(name = "cart_key")
    private Map<Long, CartLine> cart = new HashMap<>();

    public CartEntity(Long userId) {
        this.userId = userId;
    }

    // 상품의 현재 재고량 확인
    public void validateEnoughStock(int stock, int orderAmount) {
        if(stock < orderAmount) throw new StockShortageException("상품의 재고가 부족합니다");
    }

    // 상품을 카트에 추가
    public void addProductToCart(int stock, CartLine cartLine) {
        validateEnoughStock(stock, cartLine.getOrderAmount());

        Long cartKey = cartLine.getProductId();
        // 키값으로 할당된 상품이 있다면 수량만 추가
        if (cart.containsKey(cartKey)) {
            CartLine cartLineInCart = cart.get(cartLine.getProductId());
            int newOrderAmount = cartLineInCart.getOrderAmount() + cartLine.getOrderAmount();
            cart.replace(cartKey, new CartLine(cartId, cartLine.getProductId(), newOrderAmount));
        }else {
            cart.put(cartKey, cartLine);
        }
    }

    // 상품 수량을 수정
    public void modifyOrderAmount(int stock, CartLine newCartLine) {
        validateEnoughStock(stock, newCartLine.getOrderAmount());
        this.cart.replace(newCartLine.getProductId(), newCartLine);
    }

    // 카트라인을 제거
    public void removeCartLine(Long productIdInCart) {
        this.cart.remove(productIdInCart);
    }

}
