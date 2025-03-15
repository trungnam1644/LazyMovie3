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
    String sql = "INSERT INTO Movie (Title, Description, ReleaseYear, CountryID, Rating, VideoURL, TrailerURL, ThumbnailURL, UserName)"
            + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try (Connection conn = DBUtils.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

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
  public List<MovieDTO> getAllMoviesWithActorsAndGenres() {
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
public static void main(String[] args) {
        // Khởi tạo MovieDAO
        MovieDAO movieDAO = new MovieDAO();
        
        // Gọi phương thức lấy danh sách phim có diễn viên và thể loại
        List<MovieDTO> movies = movieDAO.getAllMoviesWithActorsAndGenres();
        
        // In thông tin các phim ra console để kiểm tra
        if(movies != null && !movies.isEmpty()){
            for (MovieDTO movie : movies) {
                System.out.println("--------------------------------------------------");
                System.out.println("Movie ID: " + movie.getMovieID());
                System.out.println("Title: " + movie.getTitle());
                System.out.println("Description: " + movie.getDescription());
                System.out.println("Release Year: " + movie.getReleaseYear());
                System.out.println("Country: " + movie.getCountryName());
                System.out.println("Rating: " + movie.getRating());
                System.out.println("Video URL: " + movie.getVideoURL());
                System.out.println("Trailer URL: " + movie.getTrailerURL());
                System.out.println("Thumbnail URL: " + movie.getThumbnailURL());
                System.out.println("User Name: " + movie.getUserName());
                System.out.println("Actors: " + movie.getActors());
                System.out.println("Genres: " + movie.getGenres());
                System.out.println("--------------------------------------------------\n");
            }
        } else {
            System.out.println("Không có dữ liệu phim để hiển thị!");
        }
    }

}


