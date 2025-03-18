package controller;

import User.UserDTO;
import dao.*;
import dto.MovieDTO;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MovieController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private MovieDAO movieDAO = new MovieDAO();
    private GenreDAO genreDAO = new GenreDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("addMovie".equals(action)) {
            try {
                String title = request.getParameter("title");
                String description = request.getParameter("description");
                int releaseYear = Integer.parseInt(request.getParameter("releaseYear"));
                int movieTypeID = Integer.parseInt(request.getParameter("movieType"));
                int countryID = Integer.parseInt(request.getParameter("country"));
                double rating = Double.parseDouble(request.getParameter("rating"));
                String videoUrl = request.getParameter("videoUrl");
                String thumbnailUrl = request.getParameter("thumbnailUrl");
                HttpSession session = request.getSession(true);
                UserDTO user = (UserDTO) session.getAttribute("User");
                String userName = user.getUserName();
                //lay genre tu form 
                List<Integer> genreIds = request.getParameterValues("genre") != null
                        ? Arrays.stream(request.getParameterValues("genre"))
                                .map(Integer::parseInt)
                                .collect(Collectors.toList())
                        : new ArrayList<>();
                MovieDTO newMovie = new MovieDTO(title, description, releaseYear, movieTypeID, countryID, rating, videoUrl, thumbnailUrl, userName);
                
                // Thêm phim vào database
                int movieID = movieDAO.addMovie(newMovie);
                
                if (movieID > 0) {
                    // Thêm thể loại cho phim
                    boolean genresAdded = movieDAO.setGenresForMovie(movieID, genreIds);
                    if (genresAdded) {
                        response.sendRedirect("admin1.jsp");
                    } else {
                        response.getWriter().write("Error: Could not add genres.");
                    }
                } else {
                    response.getWriter().write("Error: Could not add movie.");
                }
                
            } catch (Exception e) {
                e.printStackTrace();
                response.getWriter().write("Error: Something went wrong.");
            }

        } else if ("editMovie".equals(action)) {
            try {
                int movieID = Integer.parseInt(request.getParameter("movieID"));
                String title = request.getParameter("title");
                String description = request.getParameter("description");
                int releaseYear = Integer.parseInt(request.getParameter("releaseYear"));
                int movieTypeID = Integer.parseInt(request.getParameter("movieType"));
                int countryID = Integer.parseInt(request.getParameter("country"));
                double rating = Double.parseDouble(request.getParameter("rating"));
                String videoUrl = request.getParameter("videoUrl");
                String thumbnailUrl = request.getParameter("thumbnailUrl");
                List<Integer> genreIds = request.getParameterValues("genre") != null
                        ? Arrays.stream(request.getParameterValues("genre"))
                                .map(Integer::parseInt)
                                .collect(Collectors.toList())
                        : new ArrayList<>();

                HttpSession session = request.getSession(true);
                UserDTO user = (UserDTO) session.getAttribute("User");
                String userName = user.getUserName();

                MovieDTO updatedMovie = new MovieDTO(movieID, title, description, releaseYear, movieTypeID, countryID, rating, videoUrl, thumbnailUrl, userName);
                boolean isUpdated = movieDAO.updateMovie(updatedMovie, genreIds);

                if (isUpdated) {
                    request.getRequestDispatcher("admin1.jsp").forward(request, response);
                } else {
                    response.getWriter().write("Failed to update movie.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.getWriter().write("Error: Something went wrong.");
            }
        }
    }

protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String action = request.getParameter("action");

    if ("phimchieurap".equals(action)) {
        try {
            List<MovieDTO> phimChieuRapList = movieDAO.getMoviesForType("Phim Chiếu Rạp");
            request.setAttribute("phimchieurapList", phimChieuRapList);
            request.getRequestDispatcher("phimchieurap.jsp").forward(request, response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    } else if ("phimle".equals(action)) {
        try {
            List<MovieDTO> phimLeList = movieDAO.getMoviesForType("Phim Lẻ");
            request.setAttribute("phimleList", phimLeList);
            request.getRequestDispatcher("phimle.jsp").forward(request, response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
}