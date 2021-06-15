package com.don.shopping.domains.user.controller;

import com.don.shopping.domains.user.query.dto.ChangePasswordDto;
import com.don.shopping.domains.user.query.dto.MyPageRequestDto;
import com.don.shopping.domains.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MyPageController {

    private final UserService userService;

    @GetMapping
    public String getMyPage(Authentication authentication, Model model) {

        System.out.println("authentication = " + authentication.toString());

        String email = authentication.getName();
        model.addAttribute("user", userService.getUserInfo(email));
        return "customer/users/mypage/mypage";
    }

    @PutMapping
    public @ResponseBody String updateUserInfo(Authentication authentication, @ModelAttribute @Valid MyPageRequestDto dto) {
        String email = authentication.getName();
        userService.updateUserInfo(email, dto);
        return "회원 정보가 수정되었습니다.";
    }

    @GetMapping("/changePassword")
    public String getChangePasswordPage() {
        return "customer/users/mypage/changePassword";
    }

    @PatchMapping("/changePassword")
    public @ResponseBody String changePassword(Authentication authentication, @ModelAttribute @Valid ChangePasswordDto dto) {
        String email = authentication.getName();
        boolean result = userService.modifyPassword(email, dto);
        if (result) {
            return "SUCCESS";
        }
        return "FAIL";
    }

    @GetMapping("/withdrawal")
    public String getWithdrawalPage(Model model) {
        return "customer/users/mypage/withdrawal";
    }

    @DeleteMapping
    public @ResponseBody String deleteUser(Authentication authentication, @RequestParam @NotBlank String password) {
        String email = authentication.getName();
        boolean result = userService.deleteUser(email, password);
        if (result) {
            return "SUCCESS";
        }
        return "FAIL";
    }

}
