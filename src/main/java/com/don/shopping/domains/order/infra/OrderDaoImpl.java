package com.don.shopping.domains.order.infra;

import com.don.shopping.domains.cart.domain.CartLineEntity;
import com.don.shopping.domains.cart.domain.QCartEntity;
import com.don.shopping.domains.cart.domain.QCartLineEntity;
import com.don.shopping.domains.order.domain.*;
import com.don.shopping.domains.order.query.dao.OrderDao;
import com.don.shopping.domains.order.query.dto.OrderProductDto;
import com.don.shopping.domains.order.service.DeliveryForm;
import com.don.shopping.domains.order.service.OrderResponseDto;
import com.don.shopping.domains.product.domain.QProductEntity;
import com.don.shopping.domains.user.domain.QUserEntity;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class OrderDaoImpl implements OrderDao {

    private EntityManager em;
    private JPAQueryFactory query;

    public OrderDaoImpl(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    QUserEntity user = QUserEntity.userEntity;
    QOrderEntity order = QOrderEntity.orderEntity;
    QDeliveryEntity delivery = QDeliveryEntity.deliveryEntity;
    QCartLineEntity cartLine = QCartLineEntity.cartLineEntity;

    @Override
    public OrderResponseDto toOrderResponseDtoFromCart(Long userId, List<Long> productIdList) {

        List<CartLineEntity> cartLineList = query
                .selectFrom(cartLine)
                .where(cartLine.product.id.in(productIdList))
                .fetch();

        List<OrderProductDto> orderProductList = cartLineList
                .stream()
                .map(cl -> OrderProductDto.builder()
                        .productId(cl.getProduct().getId())
                        .productName(cl.getProduct().getProductName())
                        .rprice(cl.getProduct().getRprice())
                        .dprice(cl.getProduct().getDprice())
                        .orderAmount(cl.getOrderAmount())
                        .build()
                ).collect(Collectors.toList());

        return new OrderResponseDto(orderProductList);
    }

    @Override
    public Page<OrderEntity> findAllOrders(Pageable pageable) {
        QueryResults<OrderEntity> queryResults = query
                .select(order)
                .from(order)
                .join(order.orderer, user).fetchJoin()
                .join(order.delivery, delivery).fetchJoin()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(order.createdDate.desc())
                .fetchResults();

        List<OrderEntity> results = queryResults.getResults();
        long total = queryResults.getTotal();
        return new PageImpl<>(results, pageable, total);
    }

    @Override
    public Page<OrderEntity> findOrders(OrderStatus orderStatus, DeliveryStatus deliveryStatus, Pageable pageable) {
        QueryResults<OrderEntity> queryResults = query
                .select(order)
                .from(order)
                .join(order.orderer, user).fetchJoin()
                .join(order.delivery, delivery).fetchJoin()
                .where(eqOrderStatus(orderStatus),
                        eqDeliveryStatus(deliveryStatus))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(order.createdDate.desc())
                .fetchResults();

        List<OrderEntity> results = queryResults.getResults();
        long total = queryResults.getTotal();
        return new PageImpl<>(results, pageable, total);
    }

    @Override
    public void updateShipment(DeliveryForm deliveryForm) {
        Long deliveryId = query
                .selectFrom(order)
                .where(order.orderId.eq(deliveryForm.getOrderId()))
                .fetchOne()
                .getDelivery().getDeliveryId();
        query
                .update(delivery)
                .set(delivery.deliveryStatus, DeliveryStatus.DONE)
                .set(delivery.shipment, deliveryForm.getShipment())
                .where(delivery.deliveryId.eq(deliveryId))
                .execute();
    }

    @Override
    public List<OrderEntity> findMyOrders(Long userId) {
        return query
                .select(order)
                .from(order)
                .where(order.orderer.id.eq(userId))
                .fetch();
    }
    @Override
    public Long countPaymentSuccess() {
        return query
                .select(order.count())
                .from(order)
                .where(order.orderStatus.eq(OrderStatus.PAYMENT_SUCCESS))
                .fetchOne();
    }
    @Override
    public Long countCanceled() {
        return query
                .select(order.count())
                .from(order)
                .where(order.orderStatus.eq(OrderStatus.CANCELED))
                .fetchOne();
    }
    @Override
    public Long countReady() {
        return query
                .select(delivery.count())
                .from(delivery)
                .where(delivery.deliveryStatus.eq(DeliveryStatus.READY))
                .fetchOne();
    }
    @Override
    public Long countDone() {
        return query
                .select(delivery.count())
                .from(delivery)
                .where(delivery.deliveryStatus.eq(DeliveryStatus.DONE))
                .fetchOne();
    }




    private BooleanExpression eqOrderStatus(OrderStatus orderStatus) {
        if(orderStatus == null)
            return null;
        return order.orderStatus.eq(orderStatus);
    }

    private BooleanExpression eqDeliveryStatus(DeliveryStatus deliveryStatus) {
        if (deliveryStatus == null)
            return null;
        return order.delivery.deliveryStatus.eq(deliveryStatus);
    }

}
