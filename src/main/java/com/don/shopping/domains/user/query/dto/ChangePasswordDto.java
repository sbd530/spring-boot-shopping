package com.don.shopping.domains.user.query.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordDto {

    private String presentPassword;
    private String newPassword;
}
