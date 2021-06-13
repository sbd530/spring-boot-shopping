package com.don.shopping.domains.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String getLoginPage() {
        return "customer/users/login/login";
    }

    //로그인 처리는 스프링 시큐리티가 처리한다.

    @GetMapping("/error")
    public String getLoginErrorPage(Model model) {
        model.addAttribute("loginError", "true");
        return "customer/users/login/login";
    }
}
