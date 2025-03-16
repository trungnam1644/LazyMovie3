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
            // Nhận thông tin từ form
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            int releaseYear = Integer.parseInt(request.getParameter("releaseYear"));
            // Chuyển đổi genreIds từ mảng thành List<Integer>
            List<Integer> genreIds = request.getParameterValues("genre") != null 
                                     ? Arrays.stream(request.getParameterValues("genre"))
                                             .map(Integer::parseInt)
                                             .collect(Collectors.toList())
                                     : new ArrayList<>(); // Thay thế List.of() bằng ArrayList rỗng
            double rating = Double.parseDouble(request.getParameter("rating"));
            String videoUrl = request.getParameter("videoUrl");
            String trailerUrl = request.getParameter("trailerUrl");
            String thumbnailUrl = request.getParameter("thumbnailUrl");
            int countryID = Integer.parseInt(request.getParameter("country"));
            HttpSession session = request.getSession(true);
            UserDTO user = (UserDTO) session.getAttribute("User");
            String userName = user.getUserName();

            // Lưu phim mới vào cơ sở dữ liệu
            MovieDTO newMovie = new MovieDTO(title, description, releaseYear, countryID, rating, videoUrl, trailerUrl, thumbnailUrl, userName);
            
            boolean isMovieAdded = movieDAO.addMovie(newMovie);
            if (isMovieAdded) {
                // Sau khi thêm phim thành công, lấy movieID và thêm genre
                int movieID = movieDAO.getMovieIDByTitle(newMovie.getTitle()); // Lấy movieID của phim vừa tạo

                // Thêm genre cho movie
                movieDAO.setGenresForMovie(movieID, genreIds); // Gọi phương thức thêm genre

                // Chuyển hướng đến trang admin1.jsp hoặc hiển thị thông báo thành công
                request.getRequestDispatcher("admin1.jsp").forward(request, response);
            } else {
                // Nếu thêm phim không thành công, hiển thị lỗi
                response.getWriter().write("Failed to add movie.");
            }
        }
    }
}
