/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author trung
 */
public class CountryDTO {
    private int countryID;
    private String countryName;

    public CountryDTO(int countryID, String countryName) {
        this.countryID = countryID;
        this.countryName = countryName;
    }

    public CountryDTO() {
        
    }

    public int getCountryID() {
        return countryID;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
     @Override
    public String toString() {
        return "CountryDTO [countryID=" + countryID + ", countryName=" + countryName + "]";
    }
}
