/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.authentication;

import User.UserDAO;
import User.UserDTO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author trung
 */
public class LoginController extends HttpServlet {
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String LOGIN_PAGE = "login.jsp";
    private static final String HOME_PAGE = "home.jsp";
    private static final String ADMIN_PAGE = "admin1.jsp";
    
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8"); 
        String url = LOGIN_PAGE;
        
        try {
            String action = request.getParameter("action");
              if (action == null || action.equals("login")) {
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                UserDAO dao = new UserDAO();
                UserDTO user = dao.login(username, password);
                
                if(user != null && user.getRole().equals("User")){
                    HttpSession session = request.getSession(true);
                    session.setAttribute("User", user);                  
                    request.getRequestDispatcher("home.jsp").forward(request, response);                    
                }else if (user != null && user.getRole().equals("Admin")) {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("User", user);
                request.getRequestDispatcher("admin1.jsp").forward(request, response);                    
                }else {
                    request.setAttribute("Error_Login","Wrong user or password. Please try again!");
                request.getRequestDispatcher("login.jsp").forward(request, response);     
                }                                            
              }
        } catch (Exception e) {
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
