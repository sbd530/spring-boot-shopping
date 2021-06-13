package com.don.shopping.domains.user.controller;

import com.don.shopping.domains.user.query.dto.ChangePasswordDto;
import com.don.shopping.domains.user.query.dto.MyPageRequestDto;
import com.don.shopping.domains.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users/{userId}")
public class MyPageController {

    private final UserService userService;

    @GetMapping
    public String getMyPage(@PathVariable Long userId, Model model) {
        model.addAttribute("user", userService.getUserInfo(userId));
        return "customer/users/mypage/mypage";
    }

    @PutMapping
    public String updateUserInfo(@PathVariable Long userId, @ModelAttribute @Valid MyPageRequestDto dto) {
        userService.updateUserInfo(userId, dto);
        return "redirect:/users/" + userId;
    }

    @DeleteMapping
    public String deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return "home/home";
    }

    @GetMapping("/changePassword")
    public String getChangePasswordPage() {
        return "customer/users/mypage/changePassword";
    }

    @PatchMapping("/changePassword")
    public String changePassword(@PathVariable Long userId, @ModelAttribute @Valid ChangePasswordDto dto, HttpSession session) {
        boolean result = userService.modifyPassword(userId, dto);
        if (result) {
            session.removeAttribute("error");
            return "redirect:/users/" + userId;
        }
        session.setAttribute("error",true);
        return "redirect:/users/" + userId + "/changePassword";
    }


}
