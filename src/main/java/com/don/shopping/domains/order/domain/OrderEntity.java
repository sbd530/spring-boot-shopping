package com.don.shopping.domains.order.domain;

import com.don.shopping.common.logging.BaseEntity;
import com.don.shopping.domains.user.domain.UserEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;
    private int totalAmount;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity orderer;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<OrderLineEntity> orderLineList = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private DeliveryEntity delivery;

    @Builder
    public OrderEntity(UserEntity orderer, List<OrderLineEntity> orderLineList, DeliveryEntity delivery) {
        this.orderStatus = OrderStatus.PAYMENT_SUCCESS;
        this.orderer = orderer;
        this.delivery = delivery;
        setOrderLineList(orderLineList);
    }

    private void setOrderLineList(List<OrderLineEntity> orderLineList) {
        orderLineList.stream()
                .forEach(orderLine -> this.orderLineList.add(orderLine));
        setTotalAmount();
    }
    private void setTotalAmount() {
        this.totalAmount = this.orderLineList.stream()
                .mapToInt(orderLine -> orderLine.getOrderAmount())
                .sum();
    }

    public void cancelThisOrder() {
        if(this.delivery.getDeliveryStatus() == DeliveryStatus.DONE)
            throw new IllegalStateException("이미 배송을 완료했습니다");
        this.orderLineList.stream()
                .forEach(orderLine -> orderLine.decreaseStock());
        this.orderStatus = OrderStatus.CANCELED;
    }

}
