/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.authentication;

import User.UserDAO;
import User.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author trung
 */
public class RegisterController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {     
       response.setContentType("text/html;charset=UTF-8");
        try {
            String action = request.getParameter("action");
            
            if ("createAccount".equals(action)) {
    String userName = request.getParameter("userName");
    String fullName = request.getParameter("fullName");
    String email = request.getParameter("email");
    String phone = request.getParameter("phone");
    String password = request.getParameter("password");
    String dateOfBirthStr = request.getParameter("dateOfBirth");
    String role = "User";   
    String gender = request.getParameter("gender");
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    java.util.Date utilDate = dateFormat.parse(dateOfBirthStr);
    Date dateOfBirth = new java.sql.Date(utilDate.getTime());

    UserDTO newUser = new UserDTO(userName, fullName, email, phone, password, dateOfBirth, role, gender);
    
    
    UserDAO dao = new UserDAO();
    int rowsInserted = dao.createAccount(newUser); // Giữ nguyên kiểu int   
    UserDTO user = new UserDTO(rowsInserted, userName, fullName, email, phone, password, dateOfBirth, role, gender);       //dki xong thanh cong chay vao login luon         
       HttpSession session = request.getSession(true);
       session.setAttribute("User", user);                  
       request.getRequestDispatcher("home.jsp").forward(request, response);
}
            
        } catch (Exception e) {
            e.printStackTrace();
        }                        
    }



    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
