package com.don.shopping.domains.cart.query.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Setter
@RedisHash("cartLineDto")
@NoArgsConstructor
public class CartLineDto {

    @Id
    private Long id;

    private Long productId;
    private String filePath;
    private String productName;
    private int rprice;
    private int dprice;
    private int orderAmount;
    private int stock;

    @QueryProjection
    @Builder
    public CartLineDto(Long productId, String filePath, String productName,
                       int rprice, int dprice, int orderAmount, int stock) {
        this.productId = productId;
        this.filePath = filePath;
        this.productName = productName;
        this.rprice = rprice;
        this.dprice = dprice;
        this.orderAmount = orderAmount;
        this.stock = stock;
    }
}
