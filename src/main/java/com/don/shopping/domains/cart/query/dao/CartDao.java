package com.don.shopping.domains.cart.query.dao;

import com.don.shopping.domains.cart.domain.CartLineEntity;
import com.don.shopping.domains.cart.query.dto.CartLineDto;

import java.util.List;

public interface CartDao {

    List<CartLineDto> getCartLineDtoList(Long userId);

    List<CartLineEntity> getCartLineList(Long userId);

    void modifyOrderAmount(Long cartLineId, CartLineEntity cartLine);

}
