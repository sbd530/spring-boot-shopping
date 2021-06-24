package com.don.shopping.domains.home.service;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class SummaryDto {

    private Long paymentSuccess = 0L;
    private Long canceled = 0L;

    private Long ready = 0L;
    private Long done = 0L;

    private Long onSale = 0L;
    private Long outOfStock = 0L;

    private Integer haveToAnswer = 0;

    @Builder
    public SummaryDto(Long paymentSuccess, Long canceled, Long ready,
                      Long done, Long onSale, Long outOfStock, Integer haveToAnswer) {
        this.paymentSuccess = paymentSuccess;
        this.canceled = canceled;
        this.ready = ready;
        this.done = done;
        this.onSale = onSale;
        this.outOfStock = outOfStock;
        this.haveToAnswer = haveToAnswer;
    }
}
