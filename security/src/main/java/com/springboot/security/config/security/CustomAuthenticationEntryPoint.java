package com.springboot.security.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.security.data.dto.EntryPointErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.naming.AuthenticationException;
import java.io.IOException;
import java.util.logging.Logger;

public class CustomAuthenticationEntryPoint extends AuthenticationEntryPoint {

    private final Logger LOGGER = LoggerFactory.getLogger(CustomAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException ex) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        LOGGER.info("[commence] 인증 실패로 response.sendError 발생");

        EntryPointErrorResponse entryPointErrorResponse = new EntryPointErrorResponse();
        entryPointErrorResponse.setMsg("인증이 실패하였습니다.");

        response.setStatus(401);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(objectMapper.writeValueAsString(entryPointErrorResponse));
    }
}
