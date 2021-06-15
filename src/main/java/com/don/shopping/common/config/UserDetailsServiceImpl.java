package com.don.shopping.common.config;

import com.don.shopping.domains.user.domain.UserEntity;
import com.don.shopping.domains.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity theUser = userRepository.findFirstByEmail(username)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이메일입니다."));

        return new User(
                theUser.getEmail(),
                theUser.getPassword(),
                List.of(new SimpleGrantedAuthority(theUser.getRole().getRole()))
        );
    }
}
