package controller;

import dao.CountryDAO;
import dao.GenreDAO;
import dao.MovieDAO;
import dto.CountryDTO;
import dto.GenreDTO;
import dto.MovieTypeDTO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreatePageController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {            
            CountryDAO countryDAO = new CountryDAO();
            GenreDAO genreDAO = new GenreDAO();
            MovieDAO movieTypeDAO = new MovieDAO();            
            List<GenreDTO> genres = genreDAO.getAllGenres();
            List<CountryDTO> countries = countryDAO.getAllCountries();
            List<MovieTypeDTO> movieTypes = movieTypeDAO.getAllMovieTypes();

            request.setAttribute("countries", countries);
            request.setAttribute("genres", genres);
            request.setAttribute("movieTypes", movieTypes);
            
            request.getRequestDispatcher("addMovie.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();                    
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
        return "Create Page Controller";
    }
}
