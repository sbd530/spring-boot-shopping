package com.don.shopping.domains.order.service;

import com.don.shopping.common.vo.Shipment;
import com.don.shopping.domains.order.domain.OrderEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DeliveryForm {

    private Long orderId;
    private String ordererName;
    private String email;

    private String phoneNumber1;
    private String phoneNumber2;
    private String phoneNumber3;

    private String postNumber;
    private String address1;
    private String address2;

    private String shipmentCompany;
    private String trackingNumber;


    public DeliveryForm(OrderEntity order) {
        this.orderId = order.getOrderId();
        this.ordererName = order.getOrderer().getName();
        this.email = order.getOrderer().getEmail();
        this.phoneNumber1 = order.getOrderer().getPhoneNumber().getPhoneNumber1();
        this.phoneNumber2 = order.getOrderer().getPhoneNumber().getPhoneNumber2();
        this.phoneNumber3 = order.getOrderer().getPhoneNumber().getPhoneNumber3();
        this.postNumber = order.getDelivery().getAddress().getPostNumber();
        this.address1 = order.getDelivery().getAddress().getAddress1();
        this.address2 = order.getDelivery().getAddress().getAddress2();
        this.shipmentCompany = order.getDelivery().getShipment().getShipmentCompany();
        this.trackingNumber = order.getDelivery().getShipment().getTrackingNumber();
    }

    public Shipment getShipment() {
        return new Shipment(this.shipmentCompany, this.trackingNumber);
    }

}
