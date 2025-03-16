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
import java.util.ArrayList;
import java.util.List;
import web.utils.DBUtils;

/**
 *
 * @author trung
 */
public class MovieDAO {

   public boolean addMovie(MovieDTO movie) {
    String sql = "INSERT INTO Movie (Title, Description, ReleaseYear, CountryID, Rating, VideoURL, TrailerURL, ThumbnailURL, UserName)"
            + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try (Connection conn = DBUtils.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
         
//        System.out.println("Adding movie: " + movie.getTitle());

        stmt.setString(1, movie.getTitle());
        stmt.setString(2, movie.getDescription());
        stmt.setInt(3, movie.getReleaseYear());
        stmt.setInt(4, movie.getCountryID()); 
        stmt.setDouble(5, movie.getRating());
        stmt.setString(6, movie.getVideoURL());
        stmt.setString(7, movie.getTrailerURL());
        stmt.setString(8, movie.getThumbnailURL());
        stmt.setString(9, movie.getUserName());

        return stmt.executeUpdate() > 0;
    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}
  public List<MovieDTO> getAllMovies() {
    List<MovieDTO> movieList = new ArrayList<>();
    String sql = "SELECT m.MovieID, m.Title, m.Description, m.ReleaseYear, " +
                 "c.CountryName, " +
                 "m.Rating, m.VideoURL, m.TrailerURL, m.ThumbnailURL, m.UserName, " +
                 "(SELECT STRING_AGG(x.ActorName, ', ') " +
                 " FROM (SELECT a2.ActorName " +
                 "       FROM MovieActor ma2 " +
                 "       JOIN Actor a2 ON ma2.ActorID = a2.ActorID " +
                 "       WHERE ma2.MovieID = m.MovieID " +
                 "       GROUP BY a2.ActorName) x) AS Actors, " +
                 "(SELECT STRING_AGG(x.GenreName, ', ') " +
                 " FROM (SELECT g2.GenreName " +
                 "       FROM MovieGenre mg2 " +
                 "       JOIN Genre g2 ON mg2.GenreID = g2.GenreID " +
                 "       WHERE mg2.MovieID = m.MovieID " +
                 "       GROUP BY g2.GenreName) x) AS Genres " +
                 "FROM Movie m " +
                 "LEFT JOIN Country c ON m.CountryID = c.CountryID " +
                 "GROUP BY m.MovieID, m.Title, m.Description, m.ReleaseYear, " +
                 "         c.CountryName, " +
                 "         m.Rating, m.VideoURL, m.TrailerURL, m.ThumbnailURL, m.UserName";
    
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
            movie.setTrailerURL(rs.getString("TrailerURL"));
            movie.setThumbnailURL(rs.getString("ThumbnailURL"));
            movie.setUserName(rs.getString("UserName"));
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
    String sql = "DELETE FROM MovieGenre WHERE MovieID = ?"; // Xóa thể loại liên quan đến phim trước
    String sql2 = "DELETE FROM Movie WHERE MovieID = ?"; // Xóa phim khỏi bảng Movie

    try (Connection conn = DBUtils.getConnection()) {
        // Xóa các thể loại liên quan đến phim
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, movieID);
            stmt.executeUpdate();
        }

        // Xóa phim khỏi bảng Movie
        try (PreparedStatement stmt = conn.prepareStatement(sql2)) {
            stmt.setInt(1, movieID);
            return stmt.executeUpdate() > 0;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}

  public static void main(String[] args) {
    // Tạo đối tượng MovieDTO
    MovieDTO movie = new MovieDTO();
    movie.setTitle("Avengers: Endgame");
    movie.setDescription("The Avengers assemble once more to reverse the damage caused by Thanos.");
    movie.setReleaseYear(2019);
    movie.setCountryID(1); // Ví dụ: quốc gia với ID = 1
    movie.setRating(4.5);
    movie.setVideoURL("https://www.youtube.com/watch?v=TcMBFSGVi1c");
    movie.setTrailerURL("https://www.youtube.com/watch?v=TcMBFSGVi1c");
    movie.setThumbnailURL("https://example.com/thumbnail.jpg");
    movie.setUserName("admin1");

    // Tạo đối tượng MovieDAO để gọi phương thức addMovie
    MovieDAO movieDAO = new MovieDAO();
    
    // Gọi phương thức addMovie để thêm phim vào cơ sở dữ liệu
    boolean isAdded = movieDAO.addMovie(movie);
    
    // Kiểm tra kết quả
    if (isAdded) {
        System.out.println("Movie added successfully!");
        
        // Sau khi thêm phim thành công, thêm thể loại cho phim
        int movieID = movieDAO.getMovieIDByTitle("Avengers: Endgame"); // Lấy MovieID của phim vừa thêm
        List<Integer> genreIDs = new ArrayList<>();
        genreIDs.add(1); // Ví dụ: thể loại với ID = 1
        genreIDs.add(2); // Ví dụ: thể loại với ID = 2
        
        boolean isGenresAdded = movieDAO.setGenresForMovie(movieID, genreIDs);
        if (isGenresAdded) {
            System.out.println("Genres added successfully!");
        } else {
            System.out.println("Failed to add genres.");
        }
    } else {
        System.out.println("Failed to add movie.");
    }
}

}


