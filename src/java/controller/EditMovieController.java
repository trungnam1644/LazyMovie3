package controller;

import dao.CountryDAO;
import dao.GenreDAO;
import dao.MovieDAO;
import dto.CountryDTO;
import dto.GenreDTO;
import dto.MovieDTO;
import dto.MovieTypeDTO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet cho trang chỉnh sửa phim
 */
public class EditMovieController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            // Lấy movieID từ request
            String movieIDParam = request.getParameter("movieID");
            if (movieIDParam == null || movieIDParam.isEmpty()) {
                response.sendRedirect("admin1.jsp?error=InvalidMovieID");
                return;
            }

            int movieID = Integer.parseInt(movieIDParam);

            // Khởi tạo DAO để lấy dữ liệu
            MovieDAO movieDAO = new MovieDAO();
            CountryDAO countryDAO = new CountryDAO();
            GenreDAO genreDAO = new GenreDAO();
            MovieDAO movieTypeDAO = new MovieDAO();

            // Lấy thông tin phim
            MovieDTO movie = movieDAO.getMovieByID(movieID);
            if (movie == null) {
                response.sendRedirect("admin1.jsp?error=MovieNotFound");
                return;
            }

            // Lấy danh sách quốc gia, thể loại và loại phim
            List<CountryDTO> countries = countryDAO.getAllCountries();
            List<GenreDTO> genres = genreDAO.getAllGenres();
            List<Integer> movieGenreIDs = genreDAO.getGenresByMovieID(movieID);
            List<MovieTypeDTO> movieTypes = movieTypeDAO.getAllMovieTypes();

            // Gửi dữ liệu đến editMovie.jsp
            request.setAttribute("movie", movie);
            request.setAttribute("countries", countries);
            request.setAttribute("genres", genres);
            request.setAttribute("movieGenreIDs", movieGenreIDs);
            request.setAttribute("movieTypes", movieTypes);

            request.getRequestDispatcher("editMovie.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("admin1.jsp?error=InternalError");
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
}
