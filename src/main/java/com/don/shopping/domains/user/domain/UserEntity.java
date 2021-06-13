package com.don.shopping.domains.user.domain;

import com.don.shopping.common.vo.Address;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Getter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String name;
    private  String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Embedded //common.vo에 위치
    private PhoneNumber phoneNumber; //phoneNumber1,2,3

    @Embedded
    private Address address; //address1,2

    @Builder
    public UserEntity(String email, String name, String password, PhoneNumber phoneNumber, Address address) {
        this.role = Role.USER;
        this.email = email;
        this.name = name;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}
