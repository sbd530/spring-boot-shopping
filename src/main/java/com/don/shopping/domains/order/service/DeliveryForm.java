package com.don.shopping.domains.order.service;

import com.don.shopping.common.vo.Shipment;
import com.don.shopping.domains.order.domain.OrderEntity;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryForm {

    @NotNull
    private Long orderId;
    private String postNumber;
    private String address1;
    private String address2;

    private String shipmentCompany;
    private String trackingNumber;

    public DeliveryForm(OrderEntity order) {
        this.orderId = order.getOrderId();
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
