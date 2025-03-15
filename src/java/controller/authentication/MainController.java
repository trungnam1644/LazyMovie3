package controller.authentication;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Main Controller to handle actions
 */
public class MainController extends HttpServlet {

    private static final String ERROR_PAGE = "index.jsp";
    private static final String LOGIN_CONTROLLER = "LoginController";
    private static final String LOGOUT_CONTROLLER = "LogoutController";
    private static final String REGISTER_CONTROLLER = "RegisterController";
    private static final String SETTYPE_CONTROLLER = "SetTypeController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR_PAGE;
        String action = request.getParameter("action");

        try {
            if (action == null) {
                url = ERROR_PAGE;
            } else if (action.equals("login")) {
                url = LOGIN_CONTROLLER;
            } else if (action.equals("logout")) {
                url = LOGOUT_CONTROLLER;
            }else if (action.equals("createAccount")){
                url = REGISTER_CONTROLLER;               
            }else if(action.equals("SetType")){
                url = SETTYPE_CONTROLLER;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        
        request.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
