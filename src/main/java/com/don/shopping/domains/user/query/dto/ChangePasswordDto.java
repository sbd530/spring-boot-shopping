package com.don.shopping.domains.user.query.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ChangePasswordDto {

    @NotBlank
    private String presentPassword;
    @NotBlank
    private String newPassword;
}
