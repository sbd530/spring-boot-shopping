package com.don.shopping.domains.home.controller;

import com.don.shopping.util.AuthenticationConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final AuthenticationConverter authenticationConverter;

    @GetMapping("/home")
    public String getHomePage() {
//        Long userId = authenticationConverter.getUserFromAuthentication(authentication).getId();
        return "customer/main/home";
    }


}
