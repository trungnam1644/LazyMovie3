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

    public static MovieDTO getMovieById(int movieID) {
        MovieDTO movie = null;
        String sql = "SELECT m.MovieID, m.Title, m.Description, m.ReleaseYear, c.CountryName, m.Rating, " +
                     "m.VideoURL, m.TrailerURL, m.ThumbnailURL, m.UserName " +
                     "FROM Movie m " +
                     "JOIN Country c ON m.CountryID = c.CountryID " +
                     "WHERE m.MovieID = ?";

        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, movieID);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                int releaseYear = rs.getInt("ReleaseYear");
                String countryName = rs.getString("CountryName");
                double rating = rs.getDouble("Rating");
                String videoURL = rs.getString("VideoURL");
                String trailerURL = rs.getString("TrailerURL");
                String thumbnailURL = rs.getString("ThumbnailURL");
                String userName = rs.getString("UserName");

                // Lấy danh sách thể loại
                List<String> genres = getGenresByMovieId(movieID);

                // Lấy danh sách diễn viên
                List<String> actors = getActorsByMovieId(movieID);

                // Tạo đối tượng MovieDTO
                movie = new MovieDTO(movieID, title, description, releaseYear, countryName, rating,
                                     videoURL, trailerURL, thumbnailURL, userName, genres, actors);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movie;
    }

    // Lấy danh sách thể loại của phim
    public static List<String> getGenresByMovieId(int movieID) {
        List<String> genres = new ArrayList<>();
        String sql = "SELECT g.GenreName FROM Genre g " +
                     "JOIN MovieGenre mg ON g.GenreID = mg.GenreID " +
                     "WHERE mg.MovieID = ?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, movieID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                genres.add(rs.getString("GenreName"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return genres;
    }

    // Lấy danh sách diễn viên của phim
    public static List<String> getActorsByMovieId(int movieID) {
        List<String> actors = new ArrayList<>();
        String sql = "SELECT a.ActorName FROM Actor a " +
                     "JOIN MovieActor ma ON a.ActorID = ma.ActorID " +
                     "WHERE ma.MovieID = ?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, movieID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                actors.add(rs.getString("ActorName"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return actors;
    }

    // Lấy danh sách tất cả phim
    public static List<MovieDTO> getAllMovies() {
        List<MovieDTO> movieList = new ArrayList<>();
        String sql = "SELECT m.MovieID, m.Title, m.ReleaseYear, c.CountryName, m.Rating, m.ThumbnailURL " +
                     "FROM Movie m " +
                     "JOIN Country c ON m.CountryID = c.CountryID";

        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int movieID = rs.getInt("MovieID");
                String title = rs.getString("Title");
                int releaseYear = rs.getInt("ReleaseYear");
                String countryName = rs.getString("CountryName");
                double rating = rs.getDouble("Rating");
                String thumbnailURL = rs.getString("ThumbnailURL");

                List<String> genres = getGenresByMovieId(movieID);
                List<String> actors = getActorsByMovieId(movieID);

                MovieDTO movie = new MovieDTO(movieID, title, "", releaseYear, countryName, rating,
                                              "", "", thumbnailURL, "", genres, actors);
                movieList.add(movie);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movieList;
    }
}


