package com.don.shopping.domains.user.controller;

import com.don.shopping.domains.user.service.LoginRequestDto;
import com.don.shopping.domains.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @GetMapping("/login")
    public String getLoginPage() {
        return "customer/users/login/login";
    }

    @PostMapping("/login/check")
    public @ResponseBody String checkUser(@ModelAttribute @Valid LoginRequestDto dto) {
        boolean check = userService.validateLoginData(dto);
        if(userService.validateLoginData(dto))

            return "SUCCESS";
        return "FAIL";
    }

    //로그인 처리는 스프링 시큐리티가 처리한다.

    @PostMapping("/login")
    public String login(LoginRequestDto dto, HttpSession session) {
        Long userId = userService.findUserIdByEmail(dto.getEmail());
        session.setAttribute("userId",userId);
        session.setAttribute("email", dto.getEmail());
        return "redirect:/home";
    }

    @GetMapping("/login/error")
    public String getLoginErrorPage(Model model) {
        model.addAttribute("loginError", "true");
        return "customer/main/home";
    }
}
