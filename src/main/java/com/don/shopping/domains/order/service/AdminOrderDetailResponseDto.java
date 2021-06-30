package com.don.shopping.domains.order.service;

import com.don.shopping.domains.order.query.dto.OrderProductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class AdminOrderDetailResponseDto {

    private Long orderId;
    private List<OrderProductDto> orderProductDtoList;
    private int totalPrice;

    public AdminOrderDetailResponseDto(List<OrderProductDto> orderProductDtoList) {
        this.orderProductDtoList = orderProductDtoList;
        this.totalPrice = orderProductDtoList
                .stream()
                .mapToInt(o -> o.getTotalPrice())
                .sum();
    }
}
