/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Movie;

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
        String sql = "INSERT INTO Movie (Title, Description, ReleaseYear, CountryName, Rating, VideoURL, TrailerURL, ThumbnailURL, UserName)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, movie.getTitle());
            stmt.setString(2, movie.getDescription());
            stmt.setInt(3, movie.getReleaseYear());
            stmt.setString(4, movie.getCountryName());
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
    public List<MovieDTO> getAllMoviesWithActors() {
    List<MovieDTO> movieList = new ArrayList<>();
    String sql = "SELECT m.MovieID, m.Title, m.Description, m.ReleaseYear, " +
                 "c.CountryName, " +  // Thay vì m.CountryName
                 "m.Rating, m.VideoURL, m.TrailerURL, m.ThumbnailURL, m.UserName, " +
                 "STRING_AGG(a.ActorName, ', ') AS Actors " +
                 "FROM Movie m " +
                 "LEFT JOIN Country c ON m.CountryID = c.CountryID " + // Thêm JOIN với Country
                 "LEFT JOIN MovieActor ma ON m.MovieID = ma.MovieID " +
                 "LEFT JOIN Actor a ON ma.ActorID = a.ActorID " + // Thay vì Actors
                 "GROUP BY m.MovieID, m.Title, m.Description, m.ReleaseYear, " +
                 "c.CountryName, " +  // Thay đổi c.CountryName
                 "m.Rating, m.VideoURL, m.TrailerURL, m.ThumbnailURL, m.UserName";

    try (Connection con = DBUtils.getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            MovieDTO movie = new MovieDTO();
            movie.setMovieID(rs.getInt("MovieID"));
            movie.setTitle(rs.getString("Title"));
            movie.setDescription(rs.getString("Description"));
            movie.setReleaseYear(rs.getInt("ReleaseYear"));
            movie.setCountryName(rs.getString("CountryName")); // Thay đổi đúng cột
            movie.setRating(rs.getDouble("Rating"));
            movie.setVideoURL(rs.getString("VideoURL"));
            movie.setTrailerURL(rs.getString("TrailerURL"));
            movie.setThumbnailURL(rs.getString("ThumbnailURL"));
            movie.setUserName(rs.getString("UserName"));
            movie.setActors(rs.getString("Actors")); 
            
            movieList.add(movie);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return movieList;
}


}


