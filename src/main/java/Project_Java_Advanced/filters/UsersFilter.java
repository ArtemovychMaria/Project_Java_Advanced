package Project_Java_Advanced.filters;

import Project_Java_Advanced.FilterService;
import Project_Java_Advanced.entities.UserRoles;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.Arrays;

@WebFilter({"/cabinet","/bucket"})
public class UsersFilter implements Filter {

    FilterService filterService=FilterService.getInstance();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterService.validateCallForPage(servletRequest, servletResponse,
                filterChain, Arrays.asList(UserRoles.USER, UserRoles.ADMIN));
    }

    @Override
    public void destroy() {

    }
}
