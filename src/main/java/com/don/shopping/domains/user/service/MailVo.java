package com.don.shopping.domains.user.service;

import lombok.Getter;

import java.util.Random;

@Getter
public class MailVo {

    public static final String FROM_ADDRESS = "itwillproject1";
    public static final String SUBJECT = "인증메일입니다.";
    private final byte codeLength = 8;
    private String code;

    public MailVo() {
        Random rd = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < codeLength; i++)
            sb.append((int)(Math.random()*9));
        code = sb.toString();
    }

}
