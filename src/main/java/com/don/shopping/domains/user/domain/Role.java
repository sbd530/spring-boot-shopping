package com.don.shopping.domains.user.domain;

import lombok.Getter;

/**
 * UserEntity 에서 사용될 권한 열거타입
 */
@Getter
public enum Role {
    USER("USER"), ADMIN("ADMIN");

    String role;

    Role(String role) {
        this.role = role;
    }
}
