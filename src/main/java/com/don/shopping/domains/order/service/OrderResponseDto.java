package com.don.shopping.domains.order.service;

import com.don.shopping.domains.order.query.dto.OrderProductDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderResponseDto {

    private List<OrderProductDto> orderProductList;
    private int totalPrice;

    public OrderResponseDto(List<OrderProductDto> orderProductList) {
        this.orderProductList = orderProductList;
        this.totalPrice = orderProductList
                .stream()
                .mapToInt(orderProduct -> orderProduct.getTotalPrice())
                .sum();
    }
}
