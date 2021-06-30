package com.don.shopping.common.config;

import com.don.shopping.util.AuthenticationConverter;
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

    private final AuthenticationConverter ac;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        request.getSession().setAttribute("login", true);
        response.sendRedirect("/home");
    }
}
