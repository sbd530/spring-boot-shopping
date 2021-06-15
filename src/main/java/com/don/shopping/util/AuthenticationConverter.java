package com.don.shopping.util;

import com.don.shopping.domains.user.domain.UserEntity;
import com.don.shopping.domains.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationConverter {

    private final UserRepository userRepository;

    public UserEntity getUserFromAuthentication(Authentication authentication) {
        String email = authentication.getName();
        return userRepository.findFirstByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 계정입니다."));
    }
}
