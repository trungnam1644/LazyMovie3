package controller;


import User.*;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            UserDAO userDAO = new UserDAO();
            List<UserDTO> userList = userDAO.getAllUsers();
            
            request.setAttribute("userList", userList);
            request.getRequestDispatcher("admin2.jsp").forward(request, response);
        } catch (Exception e) {
            System.err.println("Lỗi trong UserController: " + e.getMessage());
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
