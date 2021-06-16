package com.don.shopping.domains.cart.service;

import com.don.shopping.domains.cart.domain.CartEntity;
import com.don.shopping.domains.cart.domain.CartLine;
import com.don.shopping.domains.cart.domain.CartRepository;
import com.don.shopping.domains.cart.query.dao.CartDao;
import com.don.shopping.domains.cart.query.dto.CartLineDto;
import com.don.shopping.domains.product.domain.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartDao cartDao;


    public Long createCart(Long userId) {
        return cartRepository.save(new CartEntity(userId))
                .getCartId();
    }

    // 유저의 '장바구니에 담기' 요청을 받아 처리합니다.
    public void addProductToCart(Long userId, AddToCartRequestDto dto) {
        CartEntity cartEntity = cartRepository.findFirstByUserId(userId);

        CartLine newCartLine = CartLine.builder()
                .cartId(cartEntity.getCartId())
                .productId(dto.getProductId())
                .orderAmount(dto.getOrderAmount())
                .build();
        int stock = productRepository.findById(dto.getProductId())
                .get().getStock();
        cartEntity.addProductToCart(stock, newCartLine);
    }

    // 유저의 id로 장바구니 페이지에 필요한 리스트를 반환합니다.
    public List<CartLineDto> getCartLineDtoListForCartPage(Long userId) {
        return cartDao.getCartLineDtoList(userId);
    }

    // 장바구니의 수량 변경 요청을 처리합니다.
    public void modifyOrderAmount(Long userId, ModifyOrderAmountRequestDto dto) {
        CartEntity cartEntity = cartRepository.findFirstByUserId(userId);
        int stock = productRepository.findById(dto.getProductId())
                .get().getStock();

        CartLine newCartLine = CartLine.builder()
                .cartId(cartEntity.getCartId())
                .productId(dto.getProductId())
                .orderAmount(dto.getOrderAmount())
                .build();
        cartEntity.modifyOrderAmount(stock, newCartLine);
    }

    // 여러 상품들에 대한 제거 요청을 처리합니다.
    public void removeCartLines(Long userId, List<Long> productIdList) {
        CartEntity cartEntity = cartRepository.findFirstByUserId(userId);
        productIdList.stream()
                .forEach(productId -> removeCartLine(userId, productId));
    }
    public void removeCartLine(Long userId, Long productId) {
        CartEntity cartEntity = cartRepository.findFirstByUserId(userId);
        cartEntity.removeCartLine(productId);
    }

    public void deleteCart(Long userId) {
        cartRepository.deleteByUserId(userId);
    }

}
