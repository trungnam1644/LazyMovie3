package controller;

import Movie.MovieDAO;
import Movie.MovieDTO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MovieController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action");
        String url = "admin1.jsp"; // Trang mặc định
        String url1 = "addMovie.jsp"; // Trang thêm phim

        try {
            MovieDAO movieDAO = new MovieDAO();

            if (action == null) {
                url = "error.jsp"; 
            } else if (action.equals("viewMovie")) {
                List<MovieDTO> movies = movieDAO.getAllMovies();
                System.out.println("Movies fetched: " + movies.size()); 
                for (MovieDTO m : movies) {
                    System.out.println(m.getTitle() + " - " + m.getGenres());
                }
                request.setAttribute("movies", movies);
                url = "admin1.jsp"; 
            } else if (action.equals("addMovie")) {
                
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Lỗi hệ thống!");
            url = "error.jsp"; 
        }

        // Đảm bảo chỉ gọi forward một lần
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

    @Override
    public String getServletInfo() {
        return "MovieController Servlet";
    }
}
