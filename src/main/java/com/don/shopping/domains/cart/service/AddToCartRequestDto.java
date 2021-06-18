package com.don.shopping.domains.cart.service;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AddToCartRequestDto {

    @NotBlank
    private Long productId;
    @NotBlank @Min(1)
    private Integer orderAmount;
}
