package com.don.shopping.domains.order.controller;

import com.don.shopping.domains.order.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminOrderController {

    private final AdminOrderService adminOrderService;

    // ?orderStatus={주문상태} &deliveryStatus={배송상태} ( &page={페이지번호} &size={한페이지당주문수})
    @GetMapping("/dashboard/orders")
    @Transactional(readOnly = true)
    public List<AdminOrderDto> getAdminOrderPage(
            @RequestParam(name = "orderStatus", required = false) String orderStatus,
            @RequestParam(name = "deliveryStatus", required = false) String deliveryStatus,
            @PageableDefault(size = 10, page = 0) Pageable pageable) {

        List<AdminOrderDto> adminOrderDtoList =
                adminOrderService.getFilteredOrders(orderStatus, deliveryStatus, pageable);
        return adminOrderDtoList;
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

    @PostMapping("/dashboard/orders/delivery")
    public ResponseEntity sendShipment(@ModelAttribute DeliveryForm deliveryForm) {
        System.out.println(deliveryForm.getOrderId());
        System.out.println(deliveryForm.getShipment());
        adminOrderService.addShipment(deliveryForm);
        return ResponseEntity.ok().build();
    }


    // 주문 취소
    @PutMapping("/dashboard/orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable Long orderId) {

        adminOrderService.cancelOrder(orderId);
        return "redirect:/dashboard/orders";
    }


}
