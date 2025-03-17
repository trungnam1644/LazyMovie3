package controller;

import User.UserDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteUserController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int userID = Integer.parseInt(request.getParameter("id"));
            UserDAO userDAO = new UserDAO();
            boolean success = userDAO.deleteUser(userID);
            
            if (success) {
                request.setAttribute("message", "Xóa người dùng thành công!");
            } else {
                request.setAttribute("message", "Không thể xóa người dùng!");
            }
        } catch (Exception e) {
            request.setAttribute("message", "Lỗi khi xóa người dùng: " + e.getMessage());
        }

        // Sau khi xóa, load lại danh sách người dùng
        request.getRequestDispatcher("MainController?action=viewUsers").forward(request, response);
    }
}
