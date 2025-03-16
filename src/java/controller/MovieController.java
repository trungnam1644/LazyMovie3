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

    // DAO instances for movie and genre
    private MovieDAO movieDAO = new MovieDAO();
    private GenreDAO genreDAO = new GenreDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("addMovie".equals(action)) {
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            int releaseYear = Integer.parseInt(request.getParameter("releaseYear"));
            List<Integer> genreIds = request.getParameterValues("genre") != null
                    ? Arrays.stream(request.getParameterValues("genre"))
                            .map(Integer::parseInt)
                            .collect(Collectors.toList())
                    : new ArrayList<>();
            double rating = Double.parseDouble(request.getParameter("rating"));
            String videoUrl = request.getParameter("videoUrl");
            String trailerUrl = request.getParameter("trailerUrl");
            String thumbnailUrl = request.getParameter("thumbnailUrl");
            int countryID = Integer.parseInt(request.getParameter("country"));
            HttpSession session = request.getSession(true);
            UserDTO user = (UserDTO) session.getAttribute("User");
            String userName = user.getUserName();
            MovieDTO newMovie = new MovieDTO(title, description, releaseYear, countryID, rating, videoUrl, trailerUrl, thumbnailUrl, userName);
            boolean isMovieAdded = movieDAO.addMovie(newMovie);
            if (isMovieAdded) {
                int movieID = movieDAO.getMovieIDByTitle(newMovie.getTitle());
                movieDAO.setGenresForMovie(movieID, genreIds);
                request.getRequestDispatcher("admin1.jsp").forward(request, response);
            } else {
                response.getWriter().write("Failed to add movie.");
            }
        } else if(action.equals("editMovie")){
            try {
        int movieID = Integer.parseInt(request.getParameter("movieID")); // Lấy movieID từ form
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        int releaseYear = Integer.parseInt(request.getParameter("releaseYear"));
        List<Integer> genreIds = request.getParameterValues("genre") != null
                ? Arrays.stream(request.getParameterValues("genre"))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList())
                : new ArrayList<>();
        double rating = Double.parseDouble(request.getParameter("rating"));
        String videoUrl = request.getParameter("videoUrl");
        String trailerUrl = request.getParameter("trailerUrl");
        String thumbnailUrl = request.getParameter("thumbnailUrl");
        int countryID = Integer.parseInt(request.getParameter("country"));

        HttpSession session = request.getSession(true);
        UserDTO user = (UserDTO) session.getAttribute("User");
        String userName = user.getUserName();
        System.out.println("Movie ID: " + movieID);
System.out.println("Title: " + title);
System.out.println("Description: " + description);
System.out.println("Release Year: " + releaseYear);
System.out.println("Country ID: " + countryID);
System.out.println("Rating: " + rating);
System.out.println("Video URL: " + videoUrl);
System.out.println("Trailer URL: " + trailerUrl);
System.out.println("Thumbnail URL: " + thumbnailUrl);
System.out.println("Created By: " + userName);
System.out.println("Genres: " + genreIds);


        MovieDTO updatedMovie = new MovieDTO(movieID, title, description, releaseYear, countryID, rating, videoUrl, trailerUrl, thumbnailUrl, userName);
        
        boolean isUpdated = movieDAO.updateMovie(updatedMovie, genreIds);
        if (isUpdated) {
            request.getRequestDispatcher("admin1.jsp").forward(request, response);
        } else {
            response.getWriter().write("Failed to update movie.");
        }
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
        response.getWriter().write("Error: Database driver not found.");
    } catch (Exception e) {
        e.printStackTrace();
        response.getWriter().write("Error: Something went wrong.");
    }
}
    }
    
}
