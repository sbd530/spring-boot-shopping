package com.don.shopping.common.exception;

public class StockShortageException extends RuntimeException {

    public StockShortageException() {
        super("상품의 재고가 부족합니다.");
    }

    public StockShortageException(String message) {
        super(message);
    }

}
