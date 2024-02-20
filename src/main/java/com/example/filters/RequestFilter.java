package com.example.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

@javax.servlet.annotation.WebFilter("/*")
public class RequestFilter implements Filter {

    private static final String CUSTOM_HEADER_NAME = "X-Author-Header";
    private static final String CUSTOM_HEADER_VALUE = "MadeByAlaa";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code if needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // Continue the filter chain with a response wrapper
        CustomHeaderResponseWrapper customHeaderResponseWrapper = new CustomHeaderResponseWrapper(
                (HttpServletResponse) response);
        customHeaderResponseWrapper.addHeader(CUSTOM_HEADER_NAME, CUSTOM_HEADER_VALUE);
        chain.doFilter(request, customHeaderResponseWrapper);
    }

    private static class CustomHeaderResponseWrapper extends HttpServletResponseWrapper {

        public CustomHeaderResponseWrapper(HttpServletResponse response) {
            super(response);
        }

        @Override
        public void addHeader(String name, String value) {
            // Add your custom header
            if (CUSTOM_HEADER_NAME.equalsIgnoreCase(name)) {
                value = CUSTOM_HEADER_VALUE;
            }
            super.addHeader(name, value);
        }
    }

    @Override
    public void destroy() {
        // Cleanup code if needed
    }
}
