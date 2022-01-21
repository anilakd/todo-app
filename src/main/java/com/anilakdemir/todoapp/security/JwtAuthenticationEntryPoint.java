package com.anilakdemir.todoapp.security;

import com.anilakdemir.todoapp.exception.UserNotFoundException;
import com.anilakdemir.todoapp.utils.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author anilakdemir
 * @date 18 Aralık 2021 Cumartesi
 * @time 18:36
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        String message = "Authentication is required to access this resource";
        response.getOutputStream().println("{ \"error\": \"" + message + "\"}");


    }

}
