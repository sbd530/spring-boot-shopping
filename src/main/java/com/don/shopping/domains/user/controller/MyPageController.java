package com.don.shopping.domains.user.controller;

import com.don.shopping.domains.order.service.OrderService;
import com.don.shopping.domains.user.query.dto.ChangePasswordDto;
import com.don.shopping.domains.user.query.dto.MyPageRequestDto;
import com.don.shopping.domains.user.service.UserService;
import com.don.shopping.util.AuthenticationConverter;
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
    private final OrderService orderService;
    private final AuthenticationConverter ac;

    @GetMapping
    public String getMyPage(Authentication authentication, Model model) {
        String email = authentication.getName();
        Long userId = ac.getUserFromAuthentication(authentication).getId();

        // 유저 정보
        model.addAttribute("user", userService.getUserInfo(email));
        // 나의 주문 내역
        model.addAttribute("myOrderList", orderService.getMyOrders(userId));
        // 나의 질문 내역
        model.addAttribute("myQuestionList", userService.getMyQuestions(userId));
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

    @DeleteMapping("/withdrawal")
    public @ResponseBody String deleteUser(Authentication authentication, @RequestParam @NotBlank String password) {
        String email = authentication.getName();
        boolean result = userService.deleteUser(email, password);
        if (result) {
            return "SUCCESS";
        }
        return "FAIL";
    }



}
