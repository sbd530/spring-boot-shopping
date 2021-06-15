package com.don.shopping.common.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 로그인성공 처리 클래스
 * 로그인 시 서버에서 처리할 내용을 정의합니다.
 * 작성자 : 윤병돈
 */
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        request.getSession().setAttribute("login", true);
        response.sendRedirect("/mypage");
    }
}
