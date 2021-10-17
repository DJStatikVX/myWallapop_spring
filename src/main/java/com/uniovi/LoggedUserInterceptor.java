package com.uniovi;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.uniovi.entities.User;
import com.uniovi.services.UsersService;

@Component
public class LoggedUserInterceptor implements HandlerInterceptor {
	
	private Logger logger = 
			LoggerFactory.getLogger(LoggedUserInterceptor.class);
	
	@Autowired
	private UsersService usersService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, Object handler) throws Exception {
		
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String email = auth.getName();
		User activeUser = usersService.getUserByEmail(email);
		request.getSession().setAttribute("activeUser", activeUser);
		
		// Registramos petición
		StringBuilder url = 
				new StringBuilder(request.getRequestURL().toString());
		
		if (request.getQueryString() != null) {
			url.append("?").append(request.getQueryString());
		}
		
		String username = "Alguien";
		if (activeUser != null) {
			username = activeUser.getEmail();
		}
		
		logger.info(String.format("%s ha intentado acceder a %s", 
					username, url.toString()));
		
		return true;
	}

}