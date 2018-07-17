package com.hnluchuan.staff.security;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
            HttpServletResponse response) throws AuthenticationException {
        
        String requestCaptcha = request.getParameter("validateCode");
        String genCaptcha = (String)request.getSession().getAttribute("validateCode");
        
        if (new File("/Users/kevin/code").exists()) {
            
        } else {
            if(requestCaptcha == null || !requestCaptcha.equals(genCaptcha)){
                // throw new PreAuthenticatedCredentialsNotFoundException("验证码不正确");
            }
        }
        return super.attemptAuthentication(request, response);
    }
}
