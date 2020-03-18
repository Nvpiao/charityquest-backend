package com.forever17.project.charityquest.configuration;

import com.forever17.project.charityquest.constants.CharityConstants;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * SameSite filter
 *
 * @author MingLiu (MLiu54@sheffield.ac.uk)
 * @version 1.0
 * @date 27 Feb 2020
 * @since 1.0
 */
@Component
public class CookiesFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setHeader(CharityConstants.HEADER_SAME_SITE_COOKIE, CharityConstants.HEADER_VALUE_SAME_SITE_NONE);
        chain.doFilter(request, response);
    }
}