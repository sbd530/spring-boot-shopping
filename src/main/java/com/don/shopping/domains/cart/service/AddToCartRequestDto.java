package com.don.shopping.domains.cart.service;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class AddToCartRequestDto {

    @NotNull
    private Long productId;
    @Min(1)
    private Integer orderAmount;
}
