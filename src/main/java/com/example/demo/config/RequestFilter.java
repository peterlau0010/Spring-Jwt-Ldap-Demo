package com.example.demo.config;

import com.example.demo.security.JwtRequestFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RequestFilter extends OncePerRequestFilter {

    Logger logger = LogManager.getLogger(RequestFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        long startTime = System.currentTimeMillis();

        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

        String msgId = requestWrapper.getHeader("msgId");

        if (msgId == null) {
            responseWrapper.setHeader("msgId", requestWrapper.getHeader("msgId"));
            logger.error("Missing msgId");
        }

        filterChain.doFilter(requestWrapper, responseWrapper);

        long endTime = System.currentTimeMillis();
        long requestTime = endTime - startTime;

        logBody(requestWrapper, responseWrapper);
        responseWrapper.copyBodyToResponse();

        logger.debug("Performance of {}: {} ms ", request.getRequestURI(), requestTime);
    }

    private void logBody(ContentCachingRequestWrapper request,
                         ContentCachingResponseWrapper response) throws IOException {
        String requestBody = getContent(request.getContentAsByteArray());
        logger.debug(request.getRequestURI() + " REQ: " + requestBody);

        String responseBody = getContent(response.getContentAsByteArray());
        logger.debug(request.getRequestURI() + " RES: " + responseBody);
    }

    private String getContent(byte[] content) {
        String body = new String(content);
        return body.replaceAll("[\n\t]", "");
    }

}
