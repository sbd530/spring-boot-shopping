package com.don.shopping.domains.order.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class AdminOrderResponseDto {

    private List<AdminOrderDto> orderDtoList;

}
