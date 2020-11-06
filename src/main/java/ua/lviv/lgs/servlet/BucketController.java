package ua.lviv.lgs.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.lviv.lgs.domain.Bucket;
import ua.lviv.lgs.domain.Product;
import ua.lviv.lgs.service.BucketService;
import ua.lviv.lgs.service.impl.BucketServiceImpl;


@WebServlet("/bucket")
public class BucketController extends HttpServlet {

    private	BucketService bucketService = BucketServiceImpl.getBucketService();




    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("product_id");

        HttpSession session = request.getSession();
        Integer userId = (Integer)session.getAttribute("userId");
        Bucket bucket = new Bucket(userId, Integer.parseInt(productId), new Date());
        try {
            bucketService.create(bucket);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        response.setContentType("text");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("Success");
    }
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bucketId = request.getParameter("bucketId");
        try {
            bucketService.delete(Integer.parseInt(bucketId));
        } catch (SQLException e) {
            e.printStackTrace();
        }


        response.setContentType("text");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("Success");
    }
}