package com.don.shopping.domains.order.controller;

import com.don.shopping.domains.order.service.OrderRequestDto;
import com.don.shopping.domains.order.service.OrderResponseDto;
import com.don.shopping.domains.order.service.OrderService;
import com.don.shopping.util.AuthenticationConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final AuthenticationConverter ac;

    // 장바구니 페이지 -> 구매하기 -> 구매 페이지
    // DTO 에는 각 상품의 productId 와 개수가 있습니다.
    @PostMapping("/orders")
    @ResponseBody
    public ResponseEntity getOrderPageFromCartPage(Authentication authentication,
                                                   @RequestBody OrderRequestDto orderRequestDto, Model model) {

        Long userId = ac.getUserFromAuthentication(authentication).getId();
        OrderResponseDto orderResponseDto =
                orderService.getOrderResponseDtoFromCart(userId, orderRequestDto);
//        model.addAttribute("orderResponseDto", orderResponseDto);

        return ResponseEntity.ok(orderResponseDto);
    }

    //테스트 후 삭제요함
    @GetMapping("/orders") //orders페이지 테스트
    public String get방식실험(Model model) {

        return "customer/orders/order";
    }

    // 상품 페이지 -> 구매하기 -> 구매 페이지
    @PostMapping("/orders/buy")
    public String getOrderPageByBuyButton(@ModelAttribute OrderRequestDto orderRequestDto, Model model) {

        OrderResponseDto orderResponseDto = orderService.getOrderResponseDtoByOneOrderLine(orderRequestDto);
        model.addAttribute("orderResponseDto", orderResponseDto);

        return "customer/orders/order";
    }

    // 구매 페이지 -> 결제하기 -> 주문 처리 -> 리다이렉트:주문 완료 페이지
    @PostMapping("/orders/execute")
    public String executeOrder(Authentication authentication,
                               @ModelAttribute @Valid OrderRequestDto orderRequestDto) {

        Long userId = ac.getUserFromAuthentication(authentication).getId();
        Long orderId = orderService.order(userId, orderRequestDto);

        return "redirect:/orders/order/success" + orderId;
    }

    // 주문 완료 페이지
    @GetMapping("/orders/success/{orderId}")
    public String getOrderSuccessPage(@PathVariable Long orderId, Model model) {

        model.addAttribute("orderId", orderId);
        String orderDate = LocalDateTime
                .now().format(DateTimeFormatter.ISO_DATE);
        model.addAttribute("orderDate", orderDate);

        return "customer/orders/orderSuccess";
    }

}
