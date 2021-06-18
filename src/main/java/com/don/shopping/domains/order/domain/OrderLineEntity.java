package com.don.shopping.domains.order.domain;

import com.don.shopping.domains.product.domain.ProductEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "order_line")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderLineEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderLineId;

    private int orderAmount;
    private int totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    public OrderLineEntity(ProductEntity product, int orderAmount) {
        this.product = product;
        this.orderAmount = orderAmount;
        setTotalPrice();
    }

    private void setTotalPrice() {
        if (this.product.getDprice() == 0)
            this.totalPrice = this.product.getRprice() * this.orderAmount;
        else
            this.totalPrice = this.product.getDprice() * this.orderAmount;
    }

    // OrderLine 생성 시 Product 의 stock 을 감소시킵니다.
    public void decreaseStock() {
        this.product.decreaseStock(this.orderAmount);
    }

    // OrderLine 제거 시 Product 의 stock 을 증가시킵니다.
    public void increaseStock() {
        this.product.increaseStock(this.orderAmount);
    }


}
