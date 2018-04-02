package com.movieland.mvc.service;

import com.movieland.mvc.model.Movie;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

public interface MovieService {

    Movie getMovieById(Long id);

    void addMovie(Movie movie);

    void deleteMovie(Long id);

    void updateMovie(Movie movie);

    List<Movie> getThreeRandomMovies();

    List<Movie> getMoviesByGenre(Long id);

    List<Movie> getAllMovies();

    List<Movie> getAllMoviesSortedByRating();

    List<Movie> getAllMoviesSortedByAscPrice();

    List<Movie> getAllMoviesSortedNyDescPrice();

    Movie getMovieCurrencyConverted(Long movieId, String currency);
}
