package com.don.shopping.domains.cart.controller;

import com.don.shopping.common.exception.StockShortageException;
import com.don.shopping.domains.cart.query.dto.CartLineDto;
import com.don.shopping.domains.cart.service.AddToCartRequestDto;
import com.don.shopping.domains.cart.service.CartService;
import com.don.shopping.domains.cart.service.ModifyOrderAmountRequestDto;
import com.don.shopping.util.AuthenticationConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;
    private final AuthenticationConverter ac;

    @GetMapping
    public String getCartPage(Authentication authentication, Model model) {
        Long userId = ac.getUserFromAuthentication(authentication).getId();
        List<CartLineDto> cartLineDtoList = cartService.getCartLineDtoListForCartPage(userId);
        model.addAttribute("cartLineDtoList", cartLineDtoList);
        int totalPrice = 0;
        int totalAmount = 0;
        for (CartLineDto cartLineDto : cartLineDtoList) {
            totalPrice += cartLineDto.getDprice() * cartLineDto.getOrderAmount();
            totalAmount += cartLineDto.getOrderAmount();
        }
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("totalAmount", totalAmount);
        return "customer/carts/cart";
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity addToCart(@ModelAttribute @Valid AddToCartRequestDto dto, Authentication authentication) {
        if(!authentication.isAuthenticated())
            return ResponseEntity.badRequest().build();

        Long userId = ac.getUserFromAuthentication(authentication).getId();
        cartService.addProductToCart(userId, dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @ResponseBody
    public ResponseEntity modifyCartLine(@ModelAttribute ModifyOrderAmountRequestDto dto, Authentication authentication) {
        if(!authentication.isAuthenticated())
            return ResponseEntity.badRequest().build();

        Long userId = ac.getUserFromAuthentication(authentication).getId();
        cartService.modifyOrderAmount(userId, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    @ResponseBody
    public ResponseEntity removeCartLine(@RequestParam("productId") Long productId, Authentication authentication) {
        if(!authentication.isAuthenticated())
            return ResponseEntity.badRequest().build();

        Long userId = ac.getUserFromAuthentication(authentication).getId();
        cartService.removeCartLine(userId, productId);
        return ResponseEntity.ok().build();
    }
}
