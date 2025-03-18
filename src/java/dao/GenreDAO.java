/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.GenreDTO;
import dto.MovieDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import web.utils.DBUtils;

/**
 *
 * @author trung
 */
public class GenreDAO {

    public List<GenreDTO> getAllGenres() {
        List<GenreDTO> genreList = new ArrayList<>();
        String sql = "SELECT GenreID, GenreName FROM Genre";

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int genreID = rs.getInt("GenreID");
                String genreName = rs.getString("GenreName");
                genreList.add(new GenreDTO(genreID, genreName));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return genreList;
    }


    public List<Integer> getGenresByMovieID(int movieID) throws ClassNotFoundException {
    List<Integer> genreIDs = new ArrayList<>();
    String sql = "SELECT GenreID FROM MovieGenre WHERE MovieID = ?";

    try (Connection conn = DBUtils.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, movieID);
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                genreIDs.add(rs.getInt("GenreID"));
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return genreIDs;
}  
    
    public GenreDTO searchByIdGenre(int genreID) {
        GenreDTO genre = null;
        String sql = "SELECT GenreID, GenreName FROM Genre WHERE GenreID = ?";
        
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, genreID);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                int id = rs.getInt("GenreID");
                String name = rs.getString("GenreName");
                genre = new GenreDTO(id, name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return genre;
    }
    
    
    
    
    public List<MovieDTO> getAllMovieByIDGenre(int genreID) {
        List<MovieDTO> movies = new ArrayList<>();
        String sql = "SELECT m.MovieID, m.Title, m.Description, m.ReleaseYear, m.Rating, m.ThumbnailURL " +
                     "FROM Movie m INNER JOIN MovieGenre mg ON m.MovieID = mg.MovieID " +
                     "WHERE mg.GenreID = ?";
        
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, genreID);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                int movieID = rs.getInt("MovieID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                int releaseYear = rs.getInt("ReleaseYear");
                double rating = rs.getDouble("Rating");
                String thumbnailURL = rs.getString("ThumbnailURL");
                
                movies.add(new MovieDTO(movieID, title, description, releaseYear, rating, thumbnailURL));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return movies;
    }
    public static void main(String[] args) {
        GenreDAO dao = new GenreDAO();
        int testGenreID = 1; // Thay đổi ID này để kiểm tra
        GenreDTO genre = dao.searchByIdGenre(testGenreID);
        
        if (genre != null) {
            System.out.println("Genre found: ID=" + genre.getGenreID() + ", Name=" + genre.getGenreName());
        } else {
            System.out.println("No genre found with ID=" + testGenreID);
        }
        
        List<MovieDTO> movies = dao.getAllMovieByIDGenre(testGenreID);
        System.out.println("Movies in Genre ID=" + testGenreID + ":");
        for (MovieDTO movie : movies) {
            System.out.println("- " + movie.getTitle() + " (" + movie.getReleaseYear() + ")");
        }
    }
    
}


