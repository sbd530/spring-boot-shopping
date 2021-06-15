package com.don.shopping.domains.user.service;

import lombok.Data;

@Data
public class FindPasswordRequestDto {
    private String email;
    private String code;
    private String newPassword;
}
