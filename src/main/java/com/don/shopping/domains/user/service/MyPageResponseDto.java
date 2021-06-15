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
//    private String postNumber;
//    private String address1;
//    private String address2;

//    private List<MyOrderDto> myOrderList; //주문 내역
//    private List<MyArticleDto> myArticleList; //내가 쓴 글

    public MyPageResponseDto(UserEntity userEntity) {
        this.email = userEntity.getEmail();
        this.name = userEntity.getName();
        this.phoneNumber1 = userEntity.getPhoneNumber().getPhoneNumber1();
        this.phoneNumber2 = userEntity.getPhoneNumber().getPhoneNumber2();
        this.phoneNumber3 = userEntity.getPhoneNumber().getPhoneNumber3();
        this.address = userEntity.getAddress();
//        postNumber = userEntity.getAddress().getPostNumber();
//        address1 = userEntity.getAddress().getAddress1();
//        address2 = userEntity.getAddress().getAddress2();
    }
}
