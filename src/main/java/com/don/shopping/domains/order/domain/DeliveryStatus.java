package com.don.shopping.domains.order.domain;

import lombok.Getter;

@Getter
public enum DeliveryStatus {

    READY("배송준비"), DONE("배송완료");

    String status;

    DeliveryStatus(String status) {
        this.status = status;
    }
}
