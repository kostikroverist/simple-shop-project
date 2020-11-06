package ua.lviv.lgs.filter;

import ua.lviv.lgs.domain.UserRole;
import ua.lviv.lgs.shared.FilterService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.Arrays;

@WebFilter("/cabinet.jsp")
public class FilterCabinet implements Filter {

    private FilterService filterService = FilterService.getFilterService();

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        filterService.doFilterValidation(req,resp, chain, Arrays.asList(UserRole.USER,UserRole.ADMINISTRATOR));
    }

    public void init(FilterConfig config) throws ServletException {

    }

}

//http://localhost:8080/cabinet.jsp
//http://localhost:8080/createProduct.jsp
//http://localhost:8080/bucket.jsp
//http://localhost:8080/product?id=2