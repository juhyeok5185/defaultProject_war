package com.ein.common.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class LoginFailHandler extends SimpleUrlAuthenticationFailureHandler {

  @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        String jsCode = "<script>alert('" + e.getMessage() + "'); location.href='/login';</script>";
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(jsCode);
        out.flush();
        setDefaultFailureUrl("/login?error=true");
        super.onAuthenticationFailure(request, response, e);
    }
}
