package ua.lviv.lgs.servlet;

import ua.lviv.lgs.domain.Product;
import ua.lviv.lgs.service.ProductService;
import ua.lviv.lgs.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/product")
public class ProductController extends HttpServlet {

    ProductService productService = ProductServiceImpl.getProductService();

    // to create resource (product)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String prise = request.getParameter("prise");

        Product product  = new Product(name, description, getValidatedPrice(prise));
        try {
            productService.create(product );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.setContentType("text");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("Success");
    }

    private double getValidatedPrice(String prise) {
        if(prise == null || prise.isEmpty()) {
            return 0;
        }
        return Double.parseDouble(prise);
    }

    // to get resource (product)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productId = request.getParameter("id");

        Product product = null;
        try {
            product = productService.read(Integer.parseInt(productId));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("product", product);
        request.getRequestDispatcher("singleProduct.jsp").forward(request, response);
    }

    // to update resource (product)
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        super.doPut(req, resp);
    }

    // to delete resource (product)
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        super.doDelete(req, resp);
    }

}