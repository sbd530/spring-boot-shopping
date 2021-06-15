package com.don.shopping.domains.user.controller;

import com.don.shopping.domains.user.service.SignupRequestDto;
import com.don.shopping.domains.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth/signup")
public class SignupController {

    private final UserService userService;

    @GetMapping
    public String getSignupPage() {
        return "customer/users/signup/signup";
    }

    @PostMapping
    public @ResponseBody String signup(@ModelAttribute @Valid SignupRequestDto dto) {
        userService.signup(dto);
        return "회원가입이 완료되었습니다!";
    }
}
