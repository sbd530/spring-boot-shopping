package com.don.shopping.domains.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;

    public String sendMail(String toAddress) {
        MailVo mailVo = new MailVo();
        String code = mailVo.getCode();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toAddress);
        message.setFrom(mailVo.FROM_ADDRESS);
        message.setSubject(mailVo.SUBJECT);
        message.setText("인증번호는 ["+code+"] 입니다.");
        javaMailSender.send(message);
        return code;
    }
}
