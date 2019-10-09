package io.github.demirmustafa.meetingapp.api.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class AuditLoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        filterChain.doFilter(httpServletRequest, httpServletResponse);

        final StringBuilder stringBuilder = new StringBuilder();

        final String queryString = httpServletRequest.getQueryString();
        if (StringUtils.isNoneBlank(queryString)) {
            stringBuilder
                    .append("|params:")
                    .append(queryString);
        }
        log.info("Trace:{} {} \"{} {}\"" + stringBuilder.toString(), httpServletResponse.getStatus(), httpServletRequest.getMethod(), httpServletRequest.getRequestURI());
    }
}
