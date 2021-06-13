package com.don.shopping.domains.user.controller;

import com.don.shopping.domains.user.domain.MemoryCodeRepository;
import com.don.shopping.domains.user.service.MailService;
import com.don.shopping.domains.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@RestController
@RequiredArgsConstructor
public class FindPasswordController {

    private final UserService userService;
    private final MailService mailService;
    private final MemoryCodeRepository memoryCodeRepository;

    @GetMapping("/findPassword")
    public String getFindPasswordPage() {
        return "customer/users/findPassword/findPassword";
    }

    @PostMapping("/findPassword")
    public ResponseEntity sendAuthMail(@RequestBody @Email String email, HttpSession session) {

        //이메일 존재 확인
        if (!userService.isExistEmail(email)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        //인증코드 이메일 전송
        String sentCode = mailService.sendMail(email);
        memoryCodeRepository.saveCode(email, sentCode);

        session.setAttribute("email", email);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/findPassword/auth")
    public String getCheckCodePage() {
        return "customer/users/findPassword/checkCode";
    }

    @PostMapping("/findPassword/auth")
    public String checkCode(@RequestBody String code, HttpSession session) {

        //인증코드 검증
        String email = (String) session.getAttribute("email");
        if (memoryCodeRepository.isValidCode(email, code)==false) {
            session.setAttribute("authError",true);
            return "redirect:/findPassword/auth";
        }

        Long userId = userService.findUserIdByEmail(email);
        return "redirect:/users/" + userId + "/initializePassword";
    }

    @GetMapping("/users/{userId}/initializePassword")
    public String getInitializePasswordPage(@PathVariable Long userId, Model model) {
        model.addAttribute("userId", userId);
        return "customer/users/findPassword/initializePassword";
    }

    @PatchMapping("/users/{userId}/initializePassword")
    public String initializePassword(@PathVariable Long userId, @RequestBody @NotBlank String newPassword) {

        //비밀번호 초기화
        userService.initializePassword(userId, newPassword);
        return "redirect:/login";
    }

}
