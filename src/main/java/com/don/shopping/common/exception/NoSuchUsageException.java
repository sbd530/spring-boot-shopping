package com.don.shopping.common.exception;

public class NoSuchUsageException extends IllegalArgumentException {

    public NoSuchUsageException() {super("상품이미지의 용도가 맞지 않습니다.");}//콘솔에 띄울 메세지

    public NoSuchUsageException(String message) { super(message); }



}
