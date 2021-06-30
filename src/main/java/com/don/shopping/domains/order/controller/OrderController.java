package com.don.shopping.domains.order.controller;

import com.don.shopping.domains.order.service.OrderRequestDto;
import com.don.shopping.domains.order.service.OrderResponseDto;
import com.don.shopping.domains.order.service.OrderService;
import com.don.shopping.util.AuthenticationConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final AuthenticationConverter ac;

    // 장바구니 페이지 -> 구매하기 -> 구매 페이지
    // DTO 에는 각 상품의 productId 와 개수가 있습니다.
    @PostMapping("/orders")
    public ResponseEntity getOrderPageFromCartPage(HttpSession session, Authentication authentication,
                                                 @RequestBody OrderRequestDto orderRequestDto) {
        Long userId = ac.getUserFromAuthentication(authentication).getId();
        OrderResponseDto orderResponseDto =
                orderService.getOrderResponseDtoFromCart(userId, orderRequestDto);
        session.setAttribute("orderResponseDto", orderResponseDto);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/orders")
    public ModelAndView getOrderPage(HttpSession session) {
        ModelAndView mav = new ModelAndView();
        OrderResponseDto orderResponseDto = (OrderResponseDto) session.getAttribute("orderResponseDto");
        mav.addObject("orderResponseDto", orderResponseDto);
        mav.setViewName("customer/orders/order");
        session.removeAttribute("orderResponseDto");
        return mav;
    }


    //상품 페이지 -> 구매하기 -> 구매 페이지
    @PostMapping("/orders/buy")
    public ResponseEntity getOrderPageFromProductPage(HttpSession session, Authentication authentication,
                                                   @RequestBody OrderRequestDto orderRequestDto) {
        Long userId = ac.getUserFromAuthentication(authentication).getId();
        OrderResponseDto orderResponseDto =
                orderService.getOrderResponseDtoByOneOrderLine(orderRequestDto);
        session.setAttribute("orderResponseDto", orderResponseDto);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/orders/buy")
    public ModelAndView getOrderPageFromProductPage(HttpSession session) {
        ModelAndView mav = new ModelAndView();
        OrderResponseDto orderResponseDto = (OrderResponseDto) session.getAttribute("orderResponseDto");
        mav.addObject("orderResponseDto", orderResponseDto);
        mav.setViewName("customer/orders/order");
        session.removeAttribute("orderResponseDto");
        return mav;
    }


    // 결제하기 버튼
    @PostMapping("/orders/execute")
    public String executeOrder(Authentication authentication,
                                       @RequestBody OrderRequestDto orderRequestDto) {
        Long userId = ac.getUserFromAuthentication(authentication).getId();
        Long orderId = orderService.order(userId, orderRequestDto);
        return "customer/orders/orderSuccess";
    }

    // 구매 페이지 -> 결제하기 -> 주문 처리 -> 리다이렉트:주문 완료 페이지
    @GetMapping("/orders/success")
    public ModelAndView executeOrderGet(HttpSession session) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("customer/orders/orderSuccess");
        session.removeAttribute("orderResponseDto");
        return mav;
    }

}
