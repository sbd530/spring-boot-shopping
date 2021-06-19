package com.don.shopping.domains.order.infra;

import com.don.shopping.domains.order.domain.*;
import com.don.shopping.domains.order.query.dao.OrderDao;
import com.don.shopping.domains.order.query.dto.OrderProductDto;
import com.don.shopping.domains.order.service.DeliveryForm;
import com.don.shopping.domains.order.service.OrderResponseDto;
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

    @Override
    public OrderResponseDto toOrderResponseDtoFromCart(Long userId, List<Long> productIdList) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT new com.don.shopping.domains.order.query.dto.OrderProductDto");
        sb.append("(p.id, p.productName, p.rprice, p.dprice, cl.orderAmount");
        sb.append(" FROM CartEntity c");
        sb.append(" JOIN c.cart cl");
        sb.append(" ON c.cartId = cl.cartId");
        sb.append(" JOIN UserEntity u");
        sb.append(" ON c.userId = u.userId");
        sb.append(" JOIN ProductEntity p");
        sb.append(" ON cl.productId = p.id");
        sb.append(" WHERE u.id = :userId");
        sb.append(" AND cl.productId IN :cartProductList");

        List<OrderProductDto> orderProductList = em
                .createQuery(sb.toString(), OrderProductDto.class)
                .setParameter("userId", userId)
                .setParameter("cartProductList", productIdList)
                .getResultList();

        return new OrderResponseDto(orderProductList);
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
    public void updateShipment(Long orderId, DeliveryForm deliveryForm) {
        query
                .update(order)
                .set(order.delivery.deliveryStatus, DeliveryStatus.DONE)
                .set(order.delivery.shipment, deliveryForm.getShipment())
                .execute();
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
