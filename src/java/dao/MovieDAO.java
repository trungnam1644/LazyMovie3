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
import java.util.ArrayList;
import java.util.Arrays;
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
    String sql = "DELETE FROM MovieGenre WHERE MovieID = ?"; 
    String sql2 = "DELETE FROM Movie WHERE MovieID = ?"; 

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
 public boolean updateMovie(MovieDTO movie, List<Integer> genreIds) throws ClassNotFoundException {
    boolean isUpdated = false;
    Connection conn = null;
    PreparedStatement pstmt = null;
    
    try {
        conn = DBUtils.getConnection(); 
        String sql = "UPDATE Movie SET title = ?, description = ?, releaseYear = ?, countryID = ?, rating = ?, videoUrl = ?, trailerUrl = ?, thumbnailUrl = ?, UserName = ? WHERE movieID = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, movie.getTitle());
        pstmt.setString(2, movie.getDescription());
        pstmt.setInt(3, movie.getReleaseYear());
        pstmt.setInt(4, movie.getCountryID());
        pstmt.setDouble(5, movie.getRating());
        pstmt.setString(6, movie.getVideoURL());
        pstmt.setString(7, movie.getTrailerURL());
        pstmt.setString(8, movie.getThumbnailURL());
        pstmt.setString(9, movie.getUserName());
        pstmt.setInt(10, movie.getMovieID());

        int rowsAffected = pstmt.executeUpdate();           
        if (rowsAffected > 0) {
            isUpdated = true;
        }

        // Xóa thể loại cũ và thêm thể loại mới
        if (isUpdated) {
            String deleteGenresSQL = "DELETE FROM MovieGenre WHERE movieID = ?";
            try (PreparedStatement deleteStmt = conn.prepareStatement(deleteGenresSQL)) {
                deleteStmt.setInt(1, movie.getMovieID());
                deleteStmt.executeUpdate();
            }

            String insertGenreSQL = "INSERT INTO MovieGenre (movieID, genreID) VALUES (?, ?)";
            try (PreparedStatement insertStmt = conn.prepareStatement(insertGenreSQL)) {
                for (int genreId : genreIds) {
                    insertStmt.setInt(1, movie.getMovieID());
                    insertStmt.setInt(2, genreId);
                    insertStmt.executeUpdate();
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } 
    return isUpdated;
}



    public MovieDTO getMovieByID(int movieID) {
     String sql = "SELECT MovieID, Title, Description, ReleaseYear, CountryID, Rating, VideoURL, TrailerURL, ThumbnailURL, UserName FROM Movie WHERE MovieID =  ?";
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
                    rs.getInt("CountryID"),
                    rs.getDouble("Rating"),
                    rs.getString("VideoURL"),
                    rs.getString("TrailerURL"),
                    rs.getString("ThumbnailURL"),
                    rs.getString("UserName")
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
                    rs.getDouble("Rating"),
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

    
    public static void main(String[] args) {
        MovieDAO movieDAO = new MovieDAO(); // Tạo instance của DAO
        int testMovieID = 1; // Thay đổi ID này để kiểm tra với dữ liệu trong DB
        
        MovieDTO movie = movieDAO.getMovieByID(testMovieID);
        
        if (movie != null) {
            System.out.println("Movie found:");
            System.out.println("ID: " + movie.getMovieID());
            System.out.println("Title: " + movie.getTitle());
            System.out.println("Description: " + movie.getDescription());
            System.out.println("Release Year: " + movie.getReleaseYear());
            System.out.println("Country ID: " + movie.getCountryID());
            System.out.println("Rating: " + movie.getRating());
            System.out.println("Video URL: " + movie.getVideoURL());
            System.out.println("Trailer URL: " + movie.getTrailerURL());
            System.out.println("Thumbnail URL: " + movie.getThumbnailURL());
            System.out.println("User Name: " + movie.getUserName());
        } else {
            System.out.println("No movie found with ID: " + testMovieID);
        }
    }
    
}


