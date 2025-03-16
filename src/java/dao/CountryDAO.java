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
public class CountryDAO {
        
    public List<CountryDTO> getAllCountries() {
        List<CountryDTO> countryList = new ArrayList<>();
        String sql = "SELECT * FROM Country";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                CountryDTO country = new CountryDTO();
                country.setCountryID(rs.getInt("CountryID"));
                country.setCountryName(rs.getString("CountryName"));
                countryList.add(country);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
              
        System.out.println("Danh sách quốc gia: " + countryList);
        return countryList;
    }

  
    public static void main(String[] args) {  
        CountryDAO countryDAO = new CountryDAO();
        List<CountryDTO> countryList = countryDAO.getAllCountries();
        if (countryList != null && !countryList.isEmpty()) {
            System.out.println("Có " + countryList.size() + " quốc gia trong cơ sở dữ liệu.");
            for (CountryDTO country : countryList) {
                System.out.println("Country ID: " + country.getCountryID() + ", Country Name: " + country.getCountryName());
            }
        } else {
            System.out.println("Không có quốc gia trong cơ sở dữ liệu.");
        }
    }
}
