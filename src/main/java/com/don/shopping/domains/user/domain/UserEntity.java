package com.don.shopping.domains.user.domain;

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
    private String username;
    private  String password;

    @Enumerated(EnumType.STRING)
    private Role role;



}
