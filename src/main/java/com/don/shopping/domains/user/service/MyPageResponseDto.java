package com.don.shopping.domains.user.service;

import com.don.shopping.common.vo.Address;
import com.don.shopping.domains.user.domain.UserEntity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class MyPageResponseDto {

    private String email;
    private String name;
    private String phoneNumber1;
    private String phoneNumber2;
    private String phoneNumber3;
    private Address address;

    public MyPageResponseDto(UserEntity userEntity) {
        this.email = userEntity.getEmail();
        this.name = userEntity.getName();
        this.phoneNumber1 = userEntity.getPhoneNumber().getPhoneNumber1();
        this.phoneNumber2 = userEntity.getPhoneNumber().getPhoneNumber2();
        this.phoneNumber3 = userEntity.getPhoneNumber().getPhoneNumber3();
        this.address = userEntity.getAddress();

    }
}
