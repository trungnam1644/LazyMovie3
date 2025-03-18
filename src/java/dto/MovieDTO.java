/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

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
    private String thumbnailURL;
    private String userName;
    private String actors;
    private int countryID;
    private String genres;
    private int movieTypeID;
    private String movieTypeName;

    public MovieDTO(int movieID, String title, String description, int releaseYear, int movieTypeID, int countryID, double rating, String videoUrl, String thumbnailUrl, String userName) {
        this.movieID = movieID;
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.movieTypeID = movieTypeID;
        this.countryID = countryID;
        this.rating = rating;
        this.videoURL = videoUrl;
        this.thumbnailURL = thumbnailUrl;
        this.userName = userName;

    }

    public MovieDTO(String title, String description, int releaseYear, int movieTypeID, int countryID, double rating, String videoUrl, String thumbnailUrl, String userName) {
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.movieTypeID = movieTypeID;
        this.countryID = countryID;
        this.rating = rating;
        this.videoURL = videoUrl;
        this.thumbnailURL = thumbnailUrl;
        this.userName = userName;
    }

    public MovieDTO(int movieID, String title, String description, int releaseYear, int movieTypeID, String movieTypeName, int countryID , String countryName, double rating, String videoURL, String thumbnailURL, String userName, String genres) {
        this.movieID = movieID;
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.countryName = countryName;
        this.rating = rating;
        this.videoURL = videoURL;
        this.thumbnailURL = thumbnailURL;
        this.userName = userName;
        this.countryID = countryID;
        this.genres = genres;
        this.movieTypeID = movieTypeID;
        this.movieTypeName = movieTypeName;
    }

    public MovieDTO(int movieID, String title, String description, String thumbnailURL , int releaseYear, double rating) {
        this.movieID = movieID;
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.thumbnailURL = thumbnailURL;
    }

    public MovieDTO(int movieID, String title, String description, String thumbnailURL , int releaseYear, double rating, String movieTypeName) {
        this.movieID = movieID;
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.thumbnailURL = thumbnailURL;
        this.movieTypeName = movieTypeName;
    }

    public MovieDTO(int movieID, String title, String thumbnailURL, double rating) {
    this.movieID = movieID;
    this.title = title;
    this.thumbnailURL = thumbnailURL;
    this.rating = rating;
    }

    public MovieDTO(int movieID, String title, String description, int releaseYear, String movieTypeName ,String countryName, double rating, String videoURL, String thumbnailURL, String userName, String genres) {
        this.movieID = movieID;
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.countryName = countryName;
        this.rating = rating;
        this.videoURL = videoURL;
        this.thumbnailURL = thumbnailURL;
        this.userName = userName;
        this.genres = genres;
        this.movieTypeName = movieTypeName;
    }

    public MovieDTO(int movieID, String title, String description, int releaseYear, double rating, String thumbnailURL) {   
    this.movieID = movieID;
    this.title = title;
    this.description = description;
    this.releaseYear = releaseYear;
    this.rating = rating;
    this.thumbnailURL = thumbnailURL;
    
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

    public MovieDTO(String title, String description, int releaseYear, int countryID, double rating, String videoURL, String thumbnailURL, String userName) {
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.countryID = countryID;
        this.rating = rating;
        this.videoURL = videoURL;
        this.thumbnailURL = thumbnailURL;
        this.userName = userName;

    }

    public MovieDTO() {
    }

    public MovieDTO(String title, String description, int releaseYear, double rating, String videoURL, String thumbnailURL, String userName) {
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.videoURL = videoURL;
        this.thumbnailURL = thumbnailURL;
        this.userName = userName;
    }

    public MovieDTO(int movieID, String title, String description, int releaseYear, int countryID, double rating, String videoUrl, String thumbnailUrl, String userName) {
    }

    public MovieDTO(int aInt, String string, String string0, int aInt0, String string1, String string2, String string3, String string4) {
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public int getCountryID() {
        return countryID;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    public MovieDTO(int movieID, String title, String description, int releaseYear, double rating, String videoURL, String thumbnailURL, String userName) {
        this.movieID = movieID;
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.videoURL = videoURL;
        this.thumbnailURL = thumbnailURL;
        this.userName = userName;
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
