package com.don.shopping.domains.cart.service;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;

@Getter
@Setter
public class AddToCartRequestDto {

    private Long productId;
    @Min(1)
    private Integer orderAmount;
}
