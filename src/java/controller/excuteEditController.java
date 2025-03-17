package controller;

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
 * Servlet xử lý cập nhật phim
 */
public class excuteEditController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Lấy dữ liệu từ request
            int movieID = Integer.parseInt(request.getParameter("movieID"));
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            int releaseYear = Integer.parseInt(request.getParameter("releaseYear"));
            int countryID = Integer.parseInt(request.getParameter("countryID"));
            double rating = Double.parseDouble(request.getParameter("rating"));
            String videoURL = request.getParameter("videoURL");
            String thumbnailURL = request.getParameter("thumbnailURL");
            String userName = request.getParameter("userName");
            int movieTypeID = Integer.parseInt(request.getParameter("movieTypeID")); // Lấy movieTypeID
            
            
            
            
            
            
            String[] genres = request.getParameterValues("genres");                   
            List<Integer> genreIds = new ArrayList<>();
            if (genres != null) {
                for (String genre : genres) {
                    genreIds.add(Integer.parseInt(genre));
                }
            }

            MovieDTO movie = new MovieDTO(movieID, title, description, releaseYear, movieTypeID, countryID, rating, videoURL, thumbnailURL, userName);

         
            MovieDAO movieDAO = new MovieDAO();
            boolean isUpdated = movieDAO.updateMovie(movie, genreIds);

            if (isUpdated) {
                response.sendRedirect("admin1.jsp?message=Update successful");
            } else {
                response.sendRedirect("editMovie.jsp?movieID=" + movieID + "&error=Update failed");
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("editMovie.jsp?error=Invalid number format");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("editMovie.jsp?error=Unexpected error occurred");
        }
    }
}
