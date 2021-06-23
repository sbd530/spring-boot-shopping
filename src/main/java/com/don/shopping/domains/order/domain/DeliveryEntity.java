package com.don.shopping.domains.order.domain;

import com.don.shopping.common.vo.Address;
import com.don.shopping.common.vo.Shipment;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "delivery")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeliveryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long deliveryId;

    @Embedded
    private Address address;

    @Embedded
    private Shipment shipment;

    @Enumerated(value = EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    @Builder
    public DeliveryEntity(Address address) {
        this.address = address;
        this.deliveryStatus = DeliveryStatus.READY;
    }

    //배송시 배송에 대한 정보를 shipment 에 저장합니다.
    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }

}
