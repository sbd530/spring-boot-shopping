package com.don.shopping.common.config;

import com.don.shopping.common.vo.Result;
import com.don.shopping.domains.user.domain.Role;
import com.don.shopping.util.AuthenticationConverter;
import com.don.shopping.util.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 로그인성공 처리 클래스
 * 로그인 시 서버에서 처리할 내용을 정의합니다.
 * 작성자 : 윤병돈
 */
@Component
@RequiredArgsConstructor
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtService jwtService;
    private final AuthenticationConverter ac;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
//        Result result = Result.successInstance();
//
//        UserEntity user = ac.getUserFromAuthentication(authentication);
//        Role role = user.getRole();
//
//        //Role == ADMIN 인 경우 토큰 발행
//        if(role == Role.ADMIN){
//            String token = jwtService.create("user", user, "user");
////            response.setHeader("Authorization", token);
//            response.getWriter().write(token);
//            response.setStatus(200);
//            return;
//        }

        request.getSession().setAttribute("login", true);
        response.sendRedirect("/home");
    }
}
