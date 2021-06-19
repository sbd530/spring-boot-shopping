package com.don.shopping.domains.order.query.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderProductDto {

    private Long productId;
    private String productName;
    private int rprice;
    private int dprice;
    private int orderAmount;
    private int totalPrice;

    @Builder
    @QueryProjection
    public OrderProductDto(Long productId, String productName, int rprice, int dprice, int orderAmount) {
        this.productId = productId;
        this.productName = productName;
        this.rprice = rprice;
        this.dprice = dprice;
        this.orderAmount = orderAmount;
        this.setTotalPrice(rprice, dprice, orderAmount);
    }

    private void setTotalPrice(int rprice, int dprice, int orderAmount) {
        if(dprice >= 0){
            this.totalPrice = dprice * orderAmount;
            return;
        }
        this.totalPrice = rprice * orderAmount;
    }
}
