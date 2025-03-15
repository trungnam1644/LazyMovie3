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
        String sql = "INSERT INTO Movies (Title, Description, ReleaseYear, CountryName, Rating, VideoURL, TrailerURL, ThumbnailURL, UserName)"
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
}


