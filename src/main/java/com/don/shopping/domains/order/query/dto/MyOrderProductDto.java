package com.don.shopping.domains.order.query.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MyOrderProductDto {
    private String productName;
    private int totalPrice;
}
