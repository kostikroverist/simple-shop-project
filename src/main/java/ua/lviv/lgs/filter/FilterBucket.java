package ua.lviv.lgs.filter;

import ua.lviv.lgs.domain.UserRole;
import ua.lviv.lgs.shared.FilterService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.Arrays;

@WebFilter("/bucket.jsp")
public class FilterBucket implements Filter {
    private FilterService filterService = FilterService.getFilterService();
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        filterService.doFilterValidation(req,resp, chain, Arrays.asList(UserRole.USER));
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
