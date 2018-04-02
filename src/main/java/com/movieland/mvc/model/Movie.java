package com.movieland.mvc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;


@Entity
@Repository
@Table(name = "movie")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Movie implements Comparable<Movie> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_russian")
    private String nameRussian;

    @Column(name = "name_native")
    private String nameNative;

    @Column(name = "year_of_release")
    private String yearOfRelease;

    @Column
    private String description;

    @Column
    private double rating;

    @Column
    private double price;

    @Column(name = "picture_path")
    private String picturePath;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, targetEntity = Country.class)
    @JoinTable(name = "movie_country", joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "country_id"))
    @JsonManagedReference
    private List<Country> countries;


    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, targetEntity = Genre.class)
    @JoinTable(name = "movie_genre", joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    @JsonManagedReference
    private List<Genre> genres;

    @Override
    @NonNull
    public int compareTo(Movie movieToCompare) {
        return Double.compare(rating, movieToCompare.rating);
    }

    public Movie() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameRussian() {
        return nameRussian;
    }

    public void setNameRussian(String nameRussian) {
        this.nameRussian = nameRussian;
    }

    public String getNameNative() {
        return nameNative;
    }

    public void setNameNative(String nameNative) {
        this.nameNative = nameNative;
    }

    public String getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(String yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", nameRussian='" + nameRussian + '\'' +
                ", nameNative='" + nameNative + '\'' +
                ", yearOfRelease='" + yearOfRelease + '\'' +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                ", price=" + price +
                ", picturePath='" + picturePath + '}';
    }
}
