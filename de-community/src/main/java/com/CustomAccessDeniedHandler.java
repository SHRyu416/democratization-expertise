package com;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
public class CustomAccessDeniedHandler implements AuthenticationFailureHandler {
	@Autowired
	private MessageSource messageSource;
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("CustomAccessDeniedHandler exception발생====>"+exception);
		if(exception instanceof BadCredentialsException) {
			//request.setAttribute("message", messageSource.getMessage("error.BadCredentials", null, Locale.getDefault()));
        	request.setAttribute("url", "/login/login");
        	request.setAttribute("message", "비밀번호가 올바르지 않습니다. 확인해주세요");
        	request.getRequestDispatcher("/login/message").forward(request, response);;
           //response.sendRedirect("/login/message");
        } else if(exception instanceof InternalAuthenticationServiceException) {
        	//request.setAttribute("message", messageSource.getMessage("error.InternalAuthenticationServiceException", null, Locale.getDefault()));
        	request.setAttribute("url", "/login/login");
        	//request.getRequestDispatcher("/login/message").forward(request, response);
        		//response.sendRedirect("/sign/login?result=E");
        }

	}
}
