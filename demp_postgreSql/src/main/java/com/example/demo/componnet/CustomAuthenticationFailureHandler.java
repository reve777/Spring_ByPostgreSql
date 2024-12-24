package com.example.demo.componnet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		if (exception.getMessage().contains("使用者已被停用")) {
			System.out.println("exception:  " + exception.getMessage());
			response.sendRedirect("/demo/loginerror");
		} else {
			System.out.println("exception:  " + exception.getMessage());
			response.sendRedirect("/demo/fail");
		}
	}
}
