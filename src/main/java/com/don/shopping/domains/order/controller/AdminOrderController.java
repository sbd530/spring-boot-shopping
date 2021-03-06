package com.don.shopping.domains.order.controller;

import com.don.shopping.domains.order.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminOrderController {

    private final AdminOrderService adminOrderService;

    @GetMapping("/dashboard/orders")
    public List<AdminOrderDto> getAdminOrderPage(
            @RequestParam(name = "orderStatus", required = false) String orderStatus,
            @RequestParam(name = "deliveryStatus", required = false) String deliveryStatus,
            Pageable pageable) {

        List<AdminOrderDto> adminOrderDtoList =
                adminOrderService.getFilteredOrders(orderStatus, deliveryStatus, pageable);
        return adminOrderDtoList;
    }

    @GetMapping("/dashboard/orders/{orderId}/delivery")
    public ModelAndView getDeliveryPage(@PathVariable Long orderId) {

        ModelAndView mav = new ModelAndView("dashboard/orders/delivery");
        DeliveryForm deliveryForm = adminOrderService.getDeliveryForm(orderId);
        mav.addObject("deliveryForm", deliveryForm);
        return mav;
    }

    @PostMapping("/dashboard/orders/delivery")
    public ResponseEntity sendShipment(@RequestBody DeliveryForm deliveryForm) {
        adminOrderService.addShipment(deliveryForm);
        return ResponseEntity.ok().build();
    }


    // 주문 취소
    @PutMapping("/dashboard/orders/cancel")
    public ResponseEntity cancelOrder(@RequestBody DeliveryForm deliveryForm) {
        Long orderId = deliveryForm.getOrderId();
        adminOrderService.cancelOrder(orderId);
        return ResponseEntity.ok().build();
    }


}
