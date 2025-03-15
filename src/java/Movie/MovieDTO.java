/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Movie;

import java.util.List;

/**
 *
 * @author trung
 */
public class MovieDTO {
    private int movieID;
    private String title;
    private String description;
    private int releaseYear;
    private String countryName;
    private double rating;
    private String videoURL;
    private String trailerURL;
    private String thumbnailURL;
    private String userName;
    private String actors;

    public MovieDTO(int movieID, String title, String description, int releaseYear, String countryName, double rating, String videoURL, String trailerURL, String thumbnailURL, String userName) {
        this.movieID = movieID;
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.countryName = countryName;
        this.rating = rating;
        this.videoURL = videoURL;
        this.trailerURL = trailerURL;
        this.thumbnailURL = thumbnailURL;
        this.userName = userName;
    }

    MovieDTO() {
    }

    public MovieDTO(String title, String description, int releaseYear, String country, double rating, String videoURL, String trailerURL, String thumbnailURL, String userName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getTrailerURL() {
        return trailerURL;
    }

    public void setTrailerURL(String trailerURL) {
        this.trailerURL = trailerURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    
}

