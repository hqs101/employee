package com.hnluchuan.staff.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.hnluchuan.staff.model.User;

@Component
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication au) throws IOException, ServletException {
		if (au != null && au.getPrincipal() != null && au.getPrincipal() instanceof User) {
		    User user = (User) au.getPrincipal();
		    request.getSession().setAttribute("currentUser", user);
		    request.getSession().setAttribute("logined", true);
		}

		String path = request.getServletPath();
		if (path.startsWith("/admin/")) {
			super.setDefaultTargetUrl("/admin/index");
			super.setAlwaysUseDefaultTargetUrl(true);
		}
		super.onAuthenticationSuccess(request, response, au);
    }

}
