/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.GenreDTO;
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

    public static void main(String[] args) {
        GenreDAO genreDAO = new GenreDAO();
        List<GenreDTO> genreList = genreDAO.getAllGenres();
        if (genreList != null && !genreList.isEmpty()) {
            System.out.println("Danh sách thể loại:");
            for (GenreDTO genre : genreList) {
                System.out.println("ID: " + genre.getGenreID() + ", Tên: " + genre.getGenreName());
            }
        } else {
            System.out.println("Không có thể loại nào trong cơ sở dữ liệu.");
        }
    }
}
