/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import web.utils.DBUtils;

/**
 *
 * @author trung
 */
public class MovieDAO {

 public int addMovie(MovieDTO movie) throws ClassNotFoundException {
    int movieID = -1;
    String sql = "INSERT INTO Movie (Title, Description, ReleaseYear, MovieTypeID, CountryID, Rating, VideoURL, ThumbnailURL, UserName) " +
                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try (Connection conn = DBUtils.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

        stmt.setString(1, movie.getTitle());
        stmt.setString(2, movie.getDescription());
        stmt.setInt(3, movie.getReleaseYear());
        stmt.setInt(4, movie.getMovieTypeID());
        stmt.setInt(5, movie.getCountryID());
        stmt.setDouble(6, movie.getRating());
        stmt.setString(7, movie.getVideoURL());
        stmt.setString(8, movie.getThumbnailURL());
        stmt.setString(9, movie.getUserName());

        int affectedRows = stmt.executeUpdate();
        if (affectedRows > 0) {
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    movieID = generatedKeys.getInt(1);
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return movieID; // Trả về ID của phim vừa thêm
}

 

  public List<MovieDTO> getAllMovies() {
    List<MovieDTO> movieList = new ArrayList<>();
    String sql = "SELECT m.MovieID, m.Title, m.Description, m.ReleaseYear, " +
                 "c.CountryName, m.Rating, m.VideoURL, m.ThumbnailURL, m.UserName, " +
                 "mt.MovieTypeID, mt.MovieTypeName, " +
                 "(SELECT STRING_AGG(x.ActorName, ', ') " +
                 " FROM (SELECT a2.ActorName " +
                 "       FROM MovieActor ma2 " +
                 "       JOIN Actor a2 ON ma2.ActorID = a2.ActorID " +
                 "       WHERE ma2.MovieID = m.MovieID " +
                 "       GROUP BY a2.ActorName) x) AS Actors, " +
                 "(SELECT STRING_AGG(g2.GenreName, ', ') " +
                 " FROM MovieGenre mg2 " +
                 " JOIN Genre g2 ON mg2.GenreID = g2.GenreID " +
                 " WHERE mg2.MovieID = m.MovieID) AS Genres " +
                 "FROM Movie m " +
                 "LEFT JOIN Country c ON m.CountryID = c.CountryID " +
                 "LEFT JOIN MovieType mt ON m.MovieTypeID = mt.MovieTypeID " +
                 "GROUP BY m.MovieID, m.Title, m.Description, m.ReleaseYear, " +
                 "c.CountryName, m.Rating, m.VideoURL, m.ThumbnailURL, m.UserName, " +
                 "mt.MovieTypeID, mt.MovieTypeName";
    
    try (Connection con = DBUtils.getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            MovieDTO movie = new MovieDTO();
            movie.setMovieID(rs.getInt("MovieID"));
            movie.setTitle(rs.getString("Title"));
            movie.setDescription(rs.getString("Description"));
            movie.setReleaseYear(rs.getInt("ReleaseYear"));
            movie.setCountryName(rs.getString("CountryName"));
            movie.setRating(rs.getDouble("Rating"));
            movie.setVideoURL(rs.getString("VideoURL"));            
            movie.setThumbnailURL(rs.getString("ThumbnailURL"));
            movie.setUserName(rs.getString("UserName"));
            movie.setMovieTypeID(rs.getInt("MovieTypeID"));
            movie.setMovieTypeName(rs.getString("MovieTypeName"));
            movie.setActors(rs.getString("Actors"));
            movie.setGenres(rs.getString("Genres"));

            movieList.add(movie);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return movieList;
}

  public boolean setGenresForMovie(int movieID, List<Integer> genreIDs) {
    String sql = "INSERT INTO MovieGenre (MovieID, GenreID) VALUES (?, ?)";

    try (Connection conn = DBUtils.getConnection()) {
        for (Integer genreID : genreIDs) {
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, movieID);
                stmt.setInt(2, genreID);
                stmt.executeUpdate();
            }
        }
        return true;
    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}


  public int getMovieIDByTitle(String title) {
    String sql = "SELECT MovieID FROM Movie WHERE Title = ?";
    
    try (Connection conn = DBUtils.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setString(1, title);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("MovieID");
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return -1; 
}
  
  
  public boolean deleteMovie(int movieID) {
    String deleteMovieActorSQL = "DELETE FROM MovieActor WHERE MovieID = ?";
    String deleteMovieGenreSQL = "DELETE FROM MovieGenre WHERE MovieID = ?";
    String deleteMovieSQL = "DELETE FROM Movie WHERE MovieID = ?";

    try (Connection conn = DBUtils.getConnection()) {
        conn.setAutoCommit(false); // Bắt đầu transaction để đảm bảo tính nhất quán

        // Xóa dữ liệu trong MovieActor trước
        try (PreparedStatement stmt = conn.prepareStatement(deleteMovieActorSQL)) {
            stmt.setInt(1, movieID);
            stmt.executeUpdate();
        }

        // Xóa dữ liệu trong MovieGenre
        try (PreparedStatement stmt = conn.prepareStatement(deleteMovieGenreSQL)) {
            stmt.setInt(1, movieID);
            stmt.executeUpdate();
        }

        // Xóa dữ liệu trong Movie
        try (PreparedStatement stmt = conn.prepareStatement(deleteMovieSQL)) {
            stmt.setInt(1, movieID);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                conn.commit(); 
                return true;
            } else {
                conn.rollback(); 
            }
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}

 public boolean updateMovie(MovieDTO movie, List<Integer> genreIds) throws ClassNotFoundException {
    boolean isUpdated = false;
    Connection conn = null;
    PreparedStatement pstmt = null;

    try {
        conn = DBUtils.getConnection();
        conn.setAutoCommit(false); // Bắt đầu transaction

        // Cập nhật Movie với MovieTypeID
        String sql = "UPDATE Movie SET title = ?, description = ?, releaseYear = ?, movieTypeID = ?, countryID = ?, rating = ?, videoUrl = ?, thumbnailUrl = ?, userName = ? WHERE movieID = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, movie.getTitle());
        pstmt.setString(2, movie.getDescription());
        pstmt.setInt(3, movie.getReleaseYear());
        pstmt.setInt(4, movie.getMovieTypeID()); // Fix: Thêm MovieTypeID
        pstmt.setInt(5, movie.getCountryID());
        pstmt.setDouble(6, movie.getRating());
        pstmt.setString(7, movie.getVideoURL());
        pstmt.setString(8, movie.getThumbnailURL());
        pstmt.setString(9, movie.getUserName());
        pstmt.setInt(10, movie.getMovieID());

        int rowsAffected = pstmt.executeUpdate();
        pstmt.close(); // Đóng pstmt

        if (rowsAffected > 0) {
            isUpdated = true;

            // Xóa thể loại cũ
            String deleteGenresSQL = "DELETE FROM MovieGenre WHERE movieID = ?";
            try (PreparedStatement deleteStmt = conn.prepareStatement(deleteGenresSQL)) {
                deleteStmt.setInt(1, movie.getMovieID());
                deleteStmt.executeUpdate();
            }

            // Thêm thể loại mới
            String insertGenreSQL = "INSERT INTO MovieGenre (movieID, genreID) VALUES (?, ?)";
            try (PreparedStatement insertStmt = conn.prepareStatement(insertGenreSQL)) {
                for (int genreId : genreIds) {
                    insertStmt.setInt(1, movie.getMovieID());
                    insertStmt.setInt(2, genreId);
                    insertStmt.executeUpdate();
                }
            }

            conn.commit(); // Xác nhận transaction
        } else {
            conn.rollback(); // Nếu cập nhật thất bại, rollback
        }
    } catch (SQLException e) {
        e.printStackTrace();
        try {
            if (conn != null) conn.rollback();
        } catch (SQLException rollbackEx) {
            rollbackEx.printStackTrace();
        }
    } finally {
        try {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.setAutoCommit(true);
            if (conn != null) conn.close();
        } catch (SQLException closeEx) {
            closeEx.printStackTrace();
        }
    }
    return isUpdated;
}




    public MovieDTO getMovieByID(int movieID) {
    String sql = "SELECT m.MovieID, m.Title, m.Description, m.ReleaseYear, " +
                 "m.MovieTypeID, mt.MovieTypeName, m.CountryID, c.CountryName, " +
                 "m.Rating, m.VideoURL, m.ThumbnailURL, m.UserName, " +
                 "STRING_AGG(g.GenreName, ', ') AS Genres " + // Gộp các thể loại thành chuỗi
                 "FROM Movie m " +
                 "JOIN Country c ON m.CountryID = c.CountryID " +
                 "JOIN MovieType mt ON m.MovieTypeID = mt.MovieTypeID " +
                 "LEFT JOIN MovieGenre mg ON m.MovieID = mg.MovieID " +
                 "LEFT JOIN Genre g ON mg.GenreID = g.GenreID " +
                 "WHERE m.MovieID = ? " +
                 "GROUP BY m.MovieID, m.Title, m.Description, m.ReleaseYear, " +
                 "m.MovieTypeID, mt.MovieTypeName, m.CountryID, c.CountryName, " +
                 "m.Rating, m.VideoURL, m.ThumbnailURL, m.UserName";

    try (Connection conn = DBUtils.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, movieID);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return new MovieDTO(
                    rs.getInt("MovieID"),
                    rs.getString("Title"),
                    rs.getString("Description"),
                    rs.getInt("ReleaseYear"),
                    rs.getInt("MovieTypeID"),    
                    rs.getString("MovieTypeName"), 
                    rs.getInt("CountryID"),      
                    rs.getString("CountryName"), 
                    rs.getDouble("Rating"),
                    rs.getString("VideoURL"),
                    rs.getString("ThumbnailURL"),
                    rs.getString("UserName"),
                    rs.getString("Genres")       
                );
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}


    
    public MovieDTO getMovieByID2(int movieID) {
    String sql = "SELECT m.MovieID, m.Title, m.Description, m.ReleaseYear, c.CountryName, "
               + "m.Rating, m.VideoURL, m.TrailerURL, m.ThumbnailURL, u.UserName, "
               + "(SELECT GROUP_CONCAT(a.ActorName SEPARATOR ', ') FROM Actor a "
               + " JOIN MovieActor ma ON a.ActorID = ma.ActorID WHERE ma.MovieID = m.MovieID) AS Actors, "
               + "(SELECT GROUP_CONCAT(g.GenreName SEPARATOR ', ') FROM Genre g "
               + " JOIN MovieGenre mg ON g.GenreID = mg.GenreID WHERE mg.MovieID = m.MovieID) AS Genres "
               + "FROM Movie m "
               + "JOIN Country c ON m.CountryID = c.CountryID "
               + "JOIN User u ON m.UserID = u.UserID "
               + "WHERE m.MovieID = ?";

    try (Connection conn = DBUtils.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, movieID);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                MovieDTO movie = new MovieDTO(
                    rs.getInt("MovieID"),
                    rs.getString("Title"),
                    rs.getString("Description"),
                    rs.getInt("ReleaseYear"),              
                    rs.getString("VideoURL"),
                    rs.getString("TrailerURL"),
                    rs.getString("ThumbnailURL"),
                    rs.getString("UserName")
                );
                movie.setCountryName(rs.getString("CountryName"));
                movie.setActors(rs.getString("Actors")); 
                movie.setGenres(rs.getString("Genres"));
                return movie;
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}
public List<MovieTypeDTO> getAllMovieTypes() {
        List<MovieTypeDTO> movieTypes = new ArrayList<>();
        String sql = "SELECT MovieTypeID, MovieTypeName FROM MovieType";

        try (Connection conn = DBUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("MovieTypeID");
                String name = rs.getString("MovieTypeName");
                movieTypes.add(new MovieTypeDTO(id, name));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movieTypes;
    }
    public List<MovieDTO> getMoviesForType(String movieTypeName) throws ClassNotFoundException {
    List<MovieDTO> movies = new ArrayList<>();
    String sql = "SELECT m.MovieID, m.Title, m.Description, m.ThumbnailURL, m.ReleaseYear, m.Rating, mt.MovieTypeName " +
                 "FROM Movie m " +
                 "JOIN MovieType mt ON m.MovieTypeID = mt.MovieTypeID " +
                 "WHERE mt.MovieTypeName = ?";  

    try (Connection conn = DBUtils.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, movieTypeName);  
        
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int movieID = rs.getInt("MovieID"); 

                MovieDTO movie = new MovieDTO(
                    movieID,
                    rs.getString("Title"),
                    rs.getString("Description"),
                    rs.getString("ThumbnailURL"),
                    rs.getInt("ReleaseYear"),
                    rs.getDouble("Rating"),
                    rs.getString("MovieTypeName")
                );
                movies.add(movie);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return movies;
}
   
    public static List<MovieDTO> searchMovies(String keyword) throws SQLException, ClassNotFoundException {
        List<MovieDTO> movies = new ArrayList<>();

        // Kiểm tra nếu từ khóa rỗng thì trả về danh sách rỗng
        if (keyword == null || keyword.trim().isEmpty()) {
            return movies;
        }

        String sql = "SELECT * FROM [Movie] WHERE [title] LIKE N'%' + ? + '%'";

        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // Gán giá trị tìm kiếm vào truy vấn
            ps.setString(1, keyword);

            System.out.println("📡 Đang chạy SQL: " + sql);
            System.out.println("🔍 Từ khóa: " + keyword);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int movieID = rs.getInt("movieID");
                    String title = rs.getString("title");
                    String thumbnailURL = rs.getString("thumbnailURL");
                    double rating = rs.getDouble("rating");

                    movies.add(new MovieDTO(movieID, title, thumbnailURL, rating));
                }
            }
        }

        return movies;
    }
 public static void main(String[] args) {
        try {
            // Nhập từ khóa tìm kiếm
            String keyword = "Bố Già";
            System.out.println("🔍 Đang tìm kiếm phim với từ khóa: " + keyword);

            // Gọi hàm searchMovies
            List<MovieDTO> movies = searchMovies(keyword);

            // Kiểm tra kết quả
            if (movies.isEmpty()) {
                System.out.println("❌ Không tìm thấy phim nào.");
            } else {
                System.out.println("✅ Tìm thấy " + movies.size() + " phim:");
                for (MovieDTO movie : movies) {
                    System.out.println("🎬 " + movie.getTitle() + " | Rating: " + movie.getRating());
                }
            }
        } catch (Exception e) {
            System.out.println("❌ Lỗi khi tìm kiếm phim: " + e.getMessage());
            e.printStackTrace();
        }
    }
}


