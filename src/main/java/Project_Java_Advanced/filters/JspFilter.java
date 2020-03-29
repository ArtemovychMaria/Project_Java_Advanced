package Project_Java_Advanced.filters;

import Project_Java_Advanced.FilterService;
import Project_Java_Advanced.entities.UserRoles;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebFilter("*.jsp")
public class JspFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;

        String requestURI = httpRequest.getRequestURI();
        if (requestURI.endsWith(".jsp")) {
            httpResponse.sendRedirect(httpRequest.getContextPath());
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
