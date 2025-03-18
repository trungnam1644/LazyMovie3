package controller;

import dao.GenreDAO;
import dao.MovieDAO;
import dto.GenreDTO;
import dto.MovieDTO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewGenresController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            int genreID = Integer.parseInt(request.getParameter("genreID"));
            GenreDAO genreDAO = new GenreDAO();
            List<MovieDTO> movies = genreDAO.getAllMovieByIDGenre(genreID);

           
            request.setAttribute("movies", movies);
            GenreDAO dao = new GenreDAO();
            List<GenreDTO> genres = dao.getAllGenres();
             request.setAttribute("genres", genres);
            
           
            request.getRequestDispatcher("home.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp"); 
        }
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
        return "ViewGenresController handles displaying movies by genre.";
    }
}
