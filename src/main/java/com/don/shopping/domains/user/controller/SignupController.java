package com.don.shopping.domains.user.controller;

import com.don.shopping.domains.user.service.SignupRequestDto;
import com.don.shopping.domains.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class SignupController {

    private final UserService userService;

    @GetMapping
    public String getSignupPage() {
        return "customer/users/signup/signup";
    }

    @PostMapping
    public String signup(@ModelAttribute @Valid SignupRequestDto dto) {
        userService.signup(dto);
        return "redirect:/home";
    }
}
