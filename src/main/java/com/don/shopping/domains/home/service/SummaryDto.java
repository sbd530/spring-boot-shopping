package com.don.shopping.domains.home.service;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class SummaryDto {

    private Long paymentSuccess;
    private Long canceled;

    private Long ready;
    private Long done;

    private Long onSale;
    private Long outOfStock;

    private Long haveToAnswer;

    @Builder
    public SummaryDto(Long paymentSuccess, Long canceled, Long ready,
                      Long done, Long onSale, Long outOfStock, Long haveToAnswer) {
        this.paymentSuccess = paymentSuccess;
        this.canceled = canceled;
        this.ready = ready;
        this.done = done;
        this.onSale = onSale;
        this.outOfStock = outOfStock;
        this.haveToAnswer = haveToAnswer;
    }
}
