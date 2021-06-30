package com.don.shopping.domains.user.controller;

import com.don.shopping.domains.user.domain.MemoryCodeRepository;
import com.don.shopping.domains.user.service.FindPasswordRequestDto;
import com.don.shopping.domains.user.service.MailService;
import com.don.shopping.domains.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class FindPasswordController {

    private final UserService userService;
    private final MailService mailService;
    private final MemoryCodeRepository memoryCodeRepository;

    @GetMapping("/auth/findPassword")
    public String getFindPasswordPage() {
        return "customer/users/findPassword/findPassword";
    }

    @PostMapping("/auth/findPassword")
    public @ResponseBody String sendAuthMail(@ModelAttribute FindPasswordRequestDto dto, HttpSession session) {
        String email = dto.getEmail();

        //이메일 존재 확인
        if (!userService.isExistEmail(email)) return "FAIL";
        //인증코드 이메일 전송
        String sentCode = mailService.sendMail(email);
        //메모리 저장소에 이메일과 인증코드 저장
        memoryCodeRepository.saveCode(email, sentCode);

        session.setAttribute("email", email);
        return "SUCCESS";
    }

    @GetMapping("/auth/checkCode")
    public String getCheckCodePage() {
        return "customer/users/findPassword/checkCode";
    }

    @PostMapping("/auth/checkCode")
    public @ResponseBody String checkCode(@ModelAttribute FindPasswordRequestDto dto, HttpSession session) {
        String code = dto.getCode();
        //인증코드 검증
        String email = (String) session.getAttribute("email");
        if (memoryCodeRepository.isValidCode(email, code)) {
            session.setAttribute("email",email);
            return "SUCCESS";
        }
        return "FAIL";
    }

    @GetMapping("/auth/initializePassword")
    public String getInitializePasswordPage() {
        return "customer/users/findPassword/initializePassword";
    }

    @PatchMapping("/auth/initializePassword")
    public @ResponseBody String initializePassword(@ModelAttribute FindPasswordRequestDto dto, HttpSession session) {
        String newPassword = dto.getNewPassword();
        String email = (String) session.getAttribute("email");
        //비밀번호 초기화
        userService.initializePassword(email, newPassword);
        return "SUCCESS";
    }

}
