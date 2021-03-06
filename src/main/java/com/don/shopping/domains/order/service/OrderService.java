package com.don.shopping.domains.order.service;

import com.don.shopping.domains.cart.service.CartService;
import com.don.shopping.domains.order.domain.DeliveryEntity;
import com.don.shopping.domains.order.domain.OrderEntity;
import com.don.shopping.domains.order.domain.OrderLineEntity;
import com.don.shopping.domains.order.domain.OrderRepository;
import com.don.shopping.domains.order.query.dao.OrderDao;
import com.don.shopping.domains.order.query.dto.MyOrderDto;
import com.don.shopping.domains.order.query.dto.OrderProductDto;
import com.don.shopping.domains.product.domain.ProductEntity;
import com.don.shopping.domains.product.domain.ProductRepository;
import com.don.shopping.domains.user.domain.UserEntity;
import com.don.shopping.domains.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderDao orderDao;
    private final UserService userService;
    private final ProductRepository productRepository;
    private final CartService cartService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Transactional(readOnly = true)
    public OrderResponseDto getOrderResponseDtoFromCart(Long userId, OrderRequestDto orderRequestDto) {

        List<Long> orderProductIdList = orderRequestDto.getOrderLineDtoList()
                .stream()
                .map(OrderLineDto::getProductId)
                .collect(Collectors.toList());

        return orderDao.toOrderResponseDtoFromCart(userId, orderProductIdList);
    }

    @Transactional(readOnly = true)
    public OrderResponseDto getOrderResponseDtoByOneOrderLine(OrderRequestDto orderRequestDto) {

        OrderLineDto orderLineDto = orderRequestDto.getOrderLineDtoList().get(0);

        ProductEntity productEntity = productRepository.findById(orderLineDto.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("????????? ???????????? ????????????."));
        OrderProductDto orderProductDto = OrderProductDto.builder()
                .productId(productEntity.getId())
                .productName(productEntity.getProductName())
                .rprice(productEntity.getRprice())
                .dprice(productEntity.getDprice())
                .orderAmount(orderLineDto.getOrderAmount())
                .build();

        return new OrderResponseDto(Arrays.asList(orderProductDto));
    }

    public Long order(Long ordererId, OrderRequestDto orderRequestDto) {

        for (OrderLineDto ol : orderRequestDto.getOrderLineDtoList()) {
            System.out.println(ol.getProductId());
        }

        // ????????? ??????
        UserEntity orderer = userService.findUserById(ordererId);

        // ?????? ??????
        DeliveryEntity delivery = DeliveryEntity.builder()
                .address(orderer.getAddress())
                .build();


        // ?????? ?????? ??????
        List<OrderLineEntity> orderLineList = orderRequestDto
                .getOrderLineDtoList()
                .stream()
                .map(
                    orderLineDto ->
                        new OrderLineEntity(
                                productRepository.findById(orderLineDto.getProductId()).get(),
                                orderLineDto.getOrderAmount()
                        )
                )
                .collect(Collectors.toList());

        // ?????? ?????? ??????
        orderLineList.forEach(OrderLineEntity::decreaseStock);

        // ?????? ??????
        OrderEntity order = OrderEntity.builder()
                .orderer(orderer)
                .delivery(delivery)
                .orderLineList(orderLineList)
                .build();

        // ?????????????????? ????????? ????????? ??????
        List<Long> productIdList = orderRequestDto.getOrderLineDtoList()
                .stream()
                .map(OrderLineDto::getProductId)
                .collect(Collectors.toList());
        cartService.removeCartLines(ordererId, productIdList);

        // ?????? ?????? ??????
        Long orderId = orderRepository.save(order).getOrderId();
        return orderId;
    }

    // ??????????????? ?????? ??????
    @Transactional(readOnly = true)
    public List<MyOrderDto> getMyOrders(Long userId) {

        List<OrderEntity> orderEntityList = orderDao.findMyOrders(userId);
        return orderEntityList
                .stream()
                .map(MyOrderDto::new)
                .collect(Collectors.toList());
    }


}
