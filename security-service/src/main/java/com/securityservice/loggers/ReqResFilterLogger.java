package com.securityservice.loggers;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Component
public class ReqResFilterLogger extends OncePerRequestFilter {
    private static Logger logger = LoggerFactory.getLogger(ReqResFilterLogger.class);
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ContentCachingRequestWrapper contentCachingRequestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper contentCachingResponseWrapper = new ContentCachingResponseWrapper(response);
        long startTime = System.currentTimeMillis();
        filterChain.doFilter(contentCachingRequestWrapper,contentCachingResponseWrapper);
        long timeTaken = System.currentTimeMillis() -startTime;
        String requestStr = getStrVal(contentCachingRequestWrapper.getContentAsByteArray(),request.getCharacterEncoding());
        String responseStr = getStrVal(contentCachingResponseWrapper.getContentAsByteArray(),request.getCharacterEncoding());
        logger.info("Filter LOGS: \nMETHOD = {}; \nREQUEST_URI = {}; \nREQUEST_BODY = {}; \nRESPONSE_BODY= {} \nRESPONSE_STATUS = {}; \nTIME_ELAPSED = {}"
        ,request.getMethod(), request.getRequestURI(), requestStr, responseStr, response.getStatus(), timeTaken
        );
        contentCachingResponseWrapper.copyBodyToResponse();
    }

    private String getStrVal(byte[] contentAsByteArray, String characterEncoding) {
        try {
            return new String(contentAsByteArray, characterEncoding);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
