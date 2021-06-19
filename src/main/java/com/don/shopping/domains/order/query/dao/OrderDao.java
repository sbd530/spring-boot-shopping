package com.don.shopping.domains.order.query.dao;

import java.util.List;

import com.don.shopping.domains.order.domain.DeliveryStatus;
import com.don.shopping.domains.order.domain.OrderEntity;
import com.don.shopping.domains.order.domain.OrderStatus;
import com.don.shopping.domains.order.service.DeliveryForm;
import com.don.shopping.domains.order.service.OrderResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderDao {

    OrderResponseDto toOrderResponseDtoFromCart(Long userId, List<Long> productIdList);

    Page<OrderEntity> findOrders(OrderStatus orderStatus, DeliveryStatus deliveryStatus, Pageable pageable);

    void updateShipment(Long orderId, DeliveryForm deliveryForm);

    List<OrderEntity> findMyOrders(Long userId);
}
