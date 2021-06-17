package com.don.shopping.domains.review.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@AllArgsConstructor//모든 필드에 대한 생성자를 생성합니다.
@NoArgsConstructor(access = AccessLevel.PROTECTED) //무분별한 객체 생성에 대해 한번더 체크할수 있는수단 (파라미터가 없는 생성자를 자동으로 생성)
@Getter
public class Product {
    private Long productId;
    private String productName;
}
