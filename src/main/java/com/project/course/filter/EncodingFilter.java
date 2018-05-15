package com.project.course.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by kleba on 06.05.2018.
 */
@WebFilter("/*")
public class EncodingFilter implements Filter {

    private String encodingFormat;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encodingFormat = filterConfig.getInitParameter("encoding");

        if (encodingFormat == null) {
            encodingFormat = "UTF-8";
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        String requestEncoding = httpServletRequest.getCharacterEncoding();

        if(!encodingFormat.equalsIgnoreCase(requestEncoding)){
            servletRequest.setCharacterEncoding(encodingFormat);
            servletResponse.setCharacterEncoding(encodingFormat);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}

