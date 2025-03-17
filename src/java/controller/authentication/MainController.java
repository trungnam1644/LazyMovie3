package controller.authentication;

import dao.CountryDAO;
import dto.CountryDTO;
import dao.MovieDAO;
import dto.MovieDTO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
    private static final String MOVIE_PAGE = "MovieController";
    private static final String VIEWCREATE_PAGE = "CreatePageController";
    private static final String DELETEMOVIE_PAGE = "DeleteMovieController";
    private static final String EDITMOVIE_PAGE = "excuteEditController";   
    private static final String VIEWMOVIEVIDEO_PAGE = "ViewMovieVideoController";
    private static final String USER_PAGE = "UserController";
    private static final String DELETEUSER_PAGE = "DeleteUserController";
    private static final String SEARCH_PAGE = "SearchController";
    

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
            } else if (action.equals("createAccount")) {
                url = REGISTER_CONTROLLER;
            } else if (action.equals("SetType")) {
                url = SETTYPE_CONTROLLER;
            } else if (action.equals("addMovie")) {
                url = MOVIE_PAGE;
            } else if (action.equals("createpage")) {
                url = VIEWCREATE_PAGE;
            } else if (action.equals("deleteMovie")) {
                url = DELETEMOVIE_PAGE;
            } else if (action.equals("excuteeditMovie")) {
                url = EDITMOVIE_PAGE;
            } else if (action.equals("viewMovie")) {
                MovieDAO movieDAO = new MovieDAO();
                List<MovieDTO> movieList = movieDAO.getAllMovies();
                request.setAttribute("movies", movieList);
                request.getRequestDispatcher("admin1.jsp").forward(request, response);
            } else if (action.equals("viewMovieInHome")) {               
                    MovieDAO movieDAO = new MovieDAO();
                    List<MovieDTO> movieList = movieDAO.getAllMovies();
                    request.setAttribute("movies", movieList);
                    url = "home.jsp"; 
                

            } else if (action.equals("viewMovieVideo")){
                url = VIEWMOVIEVIDEO_PAGE;
            } else if(action.equals("viewUsers")){
                url = USER_PAGE;
            }else if(action.equals("deleteUser")){
                url =DELETEUSER_PAGE;
            }else if (action.equals("search")){
                 url = SEARCH_PAGE;
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
