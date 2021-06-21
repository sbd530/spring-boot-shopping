package com.don.shopping.domains.order.domain;

import lombok.Getter;

@Getter
public enum OrderStatus {

    PAYMENT_SUCCESS("주문완료"), CANCELED("주문취소");

    private String status;

    OrderStatus(String status) {
        this.status = status;
    }
}
