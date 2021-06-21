package com.don.shopping.domains.cart.service;

import com.don.shopping.common.exception.StockShortageException;
import com.don.shopping.domains.cart.domain.CartEntity;
import com.don.shopping.domains.cart.domain.CartLineEntity;
import com.don.shopping.domains.cart.domain.CartRepository;
import com.don.shopping.domains.cart.query.dao.CartDao;
import com.don.shopping.domains.cart.query.dto.CartLineDto;
import com.don.shopping.domains.product.domain.ProductEntity;
import com.don.shopping.domains.product.domain.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartDao cartDao;
    //private final CartMapper cartMapper;


    public Long createCart(Long userId) {
        return cartRepository.save(new CartEntity(userId))
                .getCartId();
    }

    // 유저의 '장바구니에 담기' 요청을 받아 처리합니다.
    public void addProductToCart(Long userId, AddToCartRequestDto dto) {
        CartEntity cartEntity = cartRepository.findFirstByUserId(userId);
        ProductEntity product = productRepository.findById(dto.getProductId()).get();
        int stock = productRepository.findById(dto.getProductId()).get().getStock();

        CartLineEntity newCartLine = CartLineEntity.builder()
                .product(product)
                .orderAmount(dto.getOrderAmount())
                .build();
        validateEnoughStock(stock, newCartLine.getOrderAmount());
        cartEntity.addCartLine(newCartLine);
    }
    private void validateEnoughStock(int stock, int orderAmount) {
        if(stock < orderAmount) throw new StockShortageException("상품의 재고가 부족합니다");
    }


    // 유저의 id로 장바구니 페이지에 필요한 리스트를 반환합니다.
    @Transactional(readOnly = true)
    public List<CartLineDto> getCartLineDtoListForCartPage(Long userId) {
        List<CartLineDto> cartLineDtoList = cartDao.getCartLineList(userId).stream()
                .map(cl -> CartLineDto.builder()
                        .productId(cl.getProduct().getId())
                        .filePath(cl.getProduct().getProductImages().get(0).getFilePath())
                        .productName(cl.getProduct().getProductName())
                        .rprice(cl.getProduct().getRprice())
                        .dprice(cl.getProduct().getDprice())
                        .orderAmount(cl.getOrderAmount())
                        .stock(cl.getProduct().getStock())
                        .build()
                ).collect(Collectors.toList());
        return cartLineDtoList;
    }

    // 장바구니의 수량 변경 요청을 처리합니다.
    public void modifyOrderAmount(Long userId, ModifyOrderAmountRequestDto dto) {
        CartEntity cartEntity = cartRepository.findFirstByUserId(userId);

        int stock = productRepository.findById(dto.getProductId()).get().getStock();
        ProductEntity product = productRepository.findById(dto.getProductId()).get();

        CartLineEntity newCartLine = CartLineEntity.builder()
                .product(product)
                .orderAmount(dto.getOrderAmount())
                .build();
        validateEnoughStock(stock, newCartLine.getOrderAmount());

        Integer newOrderAmount = dto.getOrderAmount();
        for (int i = 0; i < cartEntity.getCartLineList().size(); i++) {
            if (cartEntity.getCartLineList().get(i).getProduct().getId() == dto.getProductId()) {
                Long cartLineId = cartEntity.getCartLineList().get(i).getCartLineId();
                cartDao.modifyOrderAmount(cartLineId, newCartLine);
                return;
            }

        }

    }

    // 여러 상품들에 대한 제거 요청을 처리합니다.
    public void removeCartLines(Long userId, List<Long> productIdList) {

        productIdList
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
