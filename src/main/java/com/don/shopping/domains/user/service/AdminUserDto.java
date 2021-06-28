package com.don.shopping.domains.user.service;

import com.don.shopping.domains.user.domain.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@AllArgsConstructor
public class AdminUserDto {

    private String name;
    private String email;

    private String phoneNumber1;
    private String phoneNumber2;
    private String phoneNumber3;

    private String postNumber;
    private String address1;
    private String address2;

    private String createdDate;

    @Builder
    public AdminUserDto(UserEntity user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.phoneNumber1 = user.getPhoneNumber().getPhoneNumber1();
        this.phoneNumber2 = user.getPhoneNumber().getPhoneNumber2();
        this.phoneNumber3 = user.getPhoneNumber().getPhoneNumber3();
        this.postNumber = user.getAddress().getPostNumber();
        this.address1 = user.getAddress().getAddress1();
        this.address2 = user.getAddress().getAddress2();
        this.createdDate = user.getCreatedDate().format(DateTimeFormatter.ofPattern("yy-MM-dd"));
    }
}
