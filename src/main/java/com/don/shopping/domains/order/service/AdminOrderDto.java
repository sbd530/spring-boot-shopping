package com.don.shopping.domains.order.service;

import com.don.shopping.common.vo.PhoneNumber;
import com.don.shopping.common.vo.Shipment;
import com.don.shopping.domains.order.domain.DeliveryStatus;
import com.don.shopping.domains.order.domain.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class AdminOrderDto {

    private Long orderId;
    private String ordererName;
    private LocalDateTime orderDate;

    private String orderStatus;
    private String deliveryStatus;

    private String phoneNumber1;
    private String phoneNumber2;
    private String phoneNumber3;

    private String shipmentCompany;
    private String trackingNumber;

    @Builder
    public AdminOrderDto(Long orderId, String ordererName, LocalDateTime orderDate,
                         PhoneNumber phoneNumber, Shipment shipment,
                         OrderStatus orderStatus, DeliveryStatus deliveryStatus) {
        this.orderId = orderId;
        this.ordererName = ordererName;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus.getStatus();
        this.deliveryStatus = deliveryStatus.getStatus();
        this.phoneNumber1 = phoneNumber.getPhoneNumber1();
        this.phoneNumber2 = phoneNumber.getPhoneNumber2();
        this.phoneNumber3 = phoneNumber.getPhoneNumber3();
        if (shipment == null) {
            shipment = new Shipment("배송회사", "송장번호");
        }
        this.shipmentCompany = shipment.getShipmentCompany();
        this.trackingNumber = shipment.getTrackingNumber();
    }
}
