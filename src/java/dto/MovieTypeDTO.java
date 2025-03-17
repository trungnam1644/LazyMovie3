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
public class MovieTypeDTO {
    private int movieTypeID;
    private String movieTypeName;

    public MovieTypeDTO(int movieTypeID, String movieTypeName) {
        this.movieTypeID = movieTypeID;
        this.movieTypeName = movieTypeName;
    }

    public int getMovieTypeID() {
        return movieTypeID;
    }

    public void setMovieTypeID(int movieTypeID) {
        this.movieTypeID = movieTypeID;
    }

    public String getMovieTypeName() {
        return movieTypeName;
    }

    public void setMovieTypeName(String movieTypeName) {
        this.movieTypeName = movieTypeName;
    }
}
