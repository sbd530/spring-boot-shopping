package com.don.shopping.domains.order.domain;

import lombok.Getter;

@Getter
public enum OrderStatus {

    PAYMENT_SUCCESS("paymentSuccess"), CANCELED("orderCanceled");

    private String status;

    OrderStatus(String status) {
        this.status = status;
    }
}
