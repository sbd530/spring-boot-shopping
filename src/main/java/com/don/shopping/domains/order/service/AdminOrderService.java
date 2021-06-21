package com.don.shopping.domains.order.service;

import com.don.shopping.domains.order.domain.DeliveryStatus;
import com.don.shopping.domains.order.domain.OrderEntity;
import com.don.shopping.domains.order.domain.OrderRepository;
import com.don.shopping.domains.order.domain.OrderStatus;
import com.don.shopping.domains.order.query.dao.OrderDao;
import com.don.shopping.domains.order.query.dto.OrderProductDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AdminOrderService {

    private OrderRepository orderRepository;
    private OrderDao orderDao;

    public void cancelOrder(Long orderId) {
        OrderEntity order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("해당 주문이 존재하지 않습니다."));
        order.cancelThisOrder();
    }

    public AdminOrderResponseDto getFilteredOrders(String orderStatusStr, String deliveryStatusStr, Pageable pageable) {

        // status 문자열이 있으면 valueOf 로 변환
        OrderStatus orderStatusEnum = OrderStatus.PAYMENT_SUCCESS;
        DeliveryStatus deliveryStatusEnum = DeliveryStatus.READY;

        if(orderStatusStr != null && !orderStatusStr.equals(""))
            orderStatusEnum = OrderStatus.valueOf(orderStatusStr);
        if(deliveryStatusStr != null && !deliveryStatusStr.equals(""))
             deliveryStatusEnum = DeliveryStatus.valueOf(deliveryStatusStr);

        Page<OrderEntity> filteredOrders = orderDao.findOrders(orderStatusEnum, deliveryStatusEnum, pageable);

        List<AdminOrderDto> adminOrderDtoList =  filteredOrders
                .stream()
                .map(order -> AdminOrderDto.builder()
                        .orderId(order.getOrderId())
                        .orderDate(order.getCreatedDate())
                        .ordererName(order.getOrderer().getName())
                        .phoneNumber(order.getOrderer().getPhoneNumber())
                        .shipment(order.getDelivery().getShipment())
                        .orderStatus(order.getOrderStatus())
                        .deliveryStatus(order.getDelivery().getDeliveryStatus())

                        .build()
                ).collect(Collectors.toList());
        int total = adminOrderDtoList.size();

        return new AdminOrderResponseDto(adminOrderDtoList, total);
    }

    public OrderResponseDto getOrderDetail(Long orderId) {
        OrderEntity order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("해당 주문이 존재하지 않습니다."));

        List<OrderProductDto> orderProductDtoList = order.getOrderLineList()
                .stream()
                .map(ol -> OrderProductDto.builder()
                        .productId(ol.getProduct().getId())
                        .productName(ol.getProduct().getProductName())
                        .orderAmount(ol.getOrderAmount())
                        .rprice(ol.getProduct().getRprice())
                        .dprice(ol.getProduct().getDprice())
                        .build()
                )
                .collect(Collectors.toList());

        return new OrderResponseDto(orderProductDtoList);
    }

    public DeliveryForm getDeliveryForm(Long orderId) {
        OrderEntity order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("해당 주문이 존재하지 않습니다."));
        return new DeliveryForm(order);
    }

    public void addShipment(Long orderId, DeliveryForm deliveryForm) {

        orderDao.updateShipment(orderId, deliveryForm);
    }
}
