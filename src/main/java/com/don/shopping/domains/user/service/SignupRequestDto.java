package com.don.shopping.domains.user.service;

import com.don.shopping.common.vo.Address;
import com.don.shopping.domains.user.domain.PhoneNumber;
import com.don.shopping.domains.user.domain.UserEntity;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class SignupRequestDto {

    @NotBlank @Email
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String name;
    @NotBlank @Length(min = 2)
    private String phoneNumber1;
    @NotBlank @Length(min = 3)
    private String phoneNumber2;
    @NotBlank @Length(min = 4)
    private String phoneNumber3;

    private String postNumber;
    private String address1;
    private String address2;

    public UserEntity toEntity() {
        UserEntity newUser = UserEntity.builder()
                .email(email)
                .password(new BCryptPasswordEncoder().encode(password))
                .name(name)
                .phoneNumber(new PhoneNumber(
                        phoneNumber1,
                        phoneNumber2,
                        phoneNumber3
                )).address(new Address(
                        postNumber,
                        address1,
                        address2
                )).build();
        return newUser;
    }

}
