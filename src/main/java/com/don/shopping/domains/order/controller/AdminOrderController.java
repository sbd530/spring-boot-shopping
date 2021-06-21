package com.don.shopping.domains.order.controller;

import com.don.shopping.domains.order.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequiredArgsConstructor
public class AdminOrderController {

    private final AdminOrderService adminOrderService;

    // ?orderStatus={주문상태} &deliveryStatus={배송상태} ( &page={페이지번호} &size={한페이지당주문수})
    @GetMapping("/dashboard/orders")
    @Transactional(readOnly = true)
    public AdminOrderResponseDto getAdminOrderPage(
            @RequestParam(name = "orderStatus", required = false) String orderStatus,
            @RequestParam(name = "deliveryStatus", required = false) String deliveryStatus,
            @PageableDefault(size = 10, page = 0) Pageable pageable) {

        AdminOrderResponseDto dto =
                adminOrderService.getFilteredOrders(orderStatus, deliveryStatus, pageable);
        return dto;
    }

    @GetMapping("/dashboard/orders/{orderId}")
    public OrderResponseDto getOrderDetail(@PathVariable Long orderId) {

        OrderResponseDto orderResponseDto = adminOrderService.getOrderDetail(orderId);
        orderResponseDto.setOrderId(orderId);
        return orderResponseDto;
    }

    @GetMapping("/dashboard/orders/{orderId}/delivery")
    public ModelAndView getDeliveryPage(@PathVariable Long orderId) {

        ModelAndView mav = new ModelAndView("dashboard/orders/delivery");
        DeliveryForm deliveryForm = adminOrderService.getDeliveryForm(orderId);
        mav.addObject("deliveryForm", deliveryForm);
        return mav;
    }

    @PostMapping("/dashboard/orders/{orderId}/delivery")
    public void sendShipment(@PathVariable Long orderId,
                                         @ModelAttribute DeliveryForm deliveryForm) {

        adminOrderService.addShipment(orderId, deliveryForm);

    }


    // 주문 취소
    @PutMapping("/dashboard/orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable Long orderId) {

        adminOrderService.cancelOrder(orderId);
        return "redirect:/dashboard/orders";
    }


}
