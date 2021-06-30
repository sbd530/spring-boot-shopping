package com.don.shopping.domains.user.query.dto;

import com.don.shopping.common.vo.Address;
import com.don.shopping.common.vo.PhoneNumber;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class MyPageRequestDto {

    @NotBlank
    private String name;
    @NotBlank @Min(2)
    private String phoneNumber1;
    @NotBlank @Min(3)
    private String phoneNumber2;
    @NotBlank @Min(4)
    private String phoneNumber3;

    private String postNumber;
    private String address1;
    private String address2;

    public PhoneNumber getPhoneNumber() {
        return new PhoneNumber(this.phoneNumber1,this.phoneNumber2,this.phoneNumber3);
    }

    public Address getAddress() {
        return new Address(this.postNumber,this.address1,this.address2);
    }
}
