package com.don.shopping.domains.order.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineDto {

    @NotBlank
    private Long productId;
    @Min(1)
    private int orderAmount;
}
