package com.don.shopping.domains.user.controller;

import com.don.shopping.domains.user.service.LoginRequestDto;
import com.don.shopping.domains.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @GetMapping("/auth/login")
    public String getLoginPage() {
        return "customer/users/login/login";
    }

    @PostMapping("/auth/login/check")
    public @ResponseBody String checkUser(@RequestBody @Valid LoginRequestDto dto) {
        boolean check = userService.validateLoginData(dto);
        if(userService.validateLoginData(dto))

            return "SUCCESS";
        return "FAIL";
    }

    //로그인 과정은 스프링 시큐리티가 처리합니다.

    @GetMapping("/auth/login/error")
    public String getLoginErrorPage(Model model) {
        model.addAttribute("loginError", "true");
        return "customer/main/home";
    }
}
