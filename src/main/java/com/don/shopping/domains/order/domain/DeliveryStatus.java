package com.don.shopping.domains.order.domain;

import lombok.Getter;

@Getter
public enum DeliveryStatus {

    READY("deliveryReady"), DONE("deliveryDone");

    String status;


    DeliveryStatus(String status) {
        this.status = status;
    }
}
