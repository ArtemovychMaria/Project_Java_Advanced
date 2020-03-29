package Project_Java_Advanced.filters;

import Project_Java_Advanced.FilterService;
import Project_Java_Advanced.entities.UserRoles;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.Arrays;

@WebFilter({"/add-product"})
public class AdminFilter implements Filter {

private FilterService filterService=FilterService.getInstance();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        filterService.validateCallForPage(request, response, chain, Arrays.asList(UserRoles.ADMIN));
}

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

}
