package ua.lviv.lgs.servlet;

import ua.lviv.lgs.domain.User;
import ua.lviv.lgs.domain.UserRole;
import ua.lviv.lgs.service.UserService;
import ua.lviv.lgs.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;


public class RegServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userServise = UserServiceImpl.getUserService();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");

        if (!email.isEmpty() && !firstName.isEmpty() && !lastName.isEmpty() && !password.isEmpty()) {
            try {
                userServise.create(new User(email, firstName, lastName, UserRole.USER.toString(), password));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        response.setContentType("type/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("success");


    }
}
