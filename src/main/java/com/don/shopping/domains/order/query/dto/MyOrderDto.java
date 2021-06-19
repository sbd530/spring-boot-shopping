package com.don.shopping.domains.order.query.dto;

import com.don.shopping.domains.order.domain.OrderEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
public class MyOrderDto {

    private LocalDateTime orderDate;
    private List<MyOrderProductDto> myOrderProductList;
    private String orderStatus;

    public MyOrderDto(OrderEntity order) {
        this.orderDate = order.getCreatedDate();
        this.myOrderProductList = order.getOrderLineList()
                .stream()
                .map(ol ->
                        new MyOrderProductDto(ol.getProduct().getProductName(),ol.getTotalPrice())
                ).collect(Collectors.toList());
        this.orderStatus = order.getOrderStatus().getStatus();
    }
}
