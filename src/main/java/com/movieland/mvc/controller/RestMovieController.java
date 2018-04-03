package com.movieland.mvc.controller;

import com.movieland.mvc.model.CurrencyType;
import com.movieland.mvc.model.Movie;
import com.movieland.mvc.model.SortType;
import com.movieland.mvc.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@CrossOrigin
public class RestMovieController {

    private MovieService movieService;

    @Autowired
    public RestMovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    //-------------Get 3 random movies---------------
    @RequestMapping(value = "movie/random", method = RequestMethod.GET)
    public ResponseEntity<List<Movie>> getThreeRandomMovies() {
        List<Movie> movieList = movieService.getThreeRandomMovies();
        if (movieList.isEmpty()) {
            return new ResponseEntity<List<Movie>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Movie>>(movieList, HttpStatus.OK);
    }

    //------------Get Movie By Id (optional USD price)---------------------
    @RequestMapping(value = "movie/{movieId}", method = RequestMethod.GET)
    public ResponseEntity<Movie> getMovieById(@PathVariable(value = "movieId") long id,
                                              @RequestParam(value = "currency", required = false) String currency) {
        Movie movie = null;
        if (currency == null) {
            movie = movieService.getMovieById(id);
        } else if (currency.equals(CurrencyType.USD.value())) {
            movie = movieService.getMovieCurrencyConverted(id);
        }

        if (movie != null) {
            return new ResponseEntity<Movie>(movie, HttpStatus.OK);
        }
        return new ResponseEntity<Movie>(HttpStatus.NOT_FOUND);
    }

    //-------------Get All Movies (optional Sorted)----------------
    @RequestMapping(value = "movie", method = RequestMethod.GET)
    public ResponseEntity<List<Movie>> getAllMoviesSortedByRating(@RequestParam(value = "rating", required = false) String rating,
                                                                  @RequestParam(value = "price", required = false) String price) {
        List<Movie> movieList = null;
        if (rating == null && price == null) {
            movieList = movieService.getAllMovies();
        } else if (price == null && rating.equals(SortType.DESC.value())) {
            movieList = movieService.getAllMoviesSortedByRating();
        } else if (rating == null && price.equals(SortType.ASC.value())) {
            movieList = movieService.getAllMoviesSortedByAscPrice();
        } else if (rating == null && price.equals(SortType.DESC.value())) {
            movieList = movieService.getAllMoviesSortedNyDescPrice();
        }

        if (movieList.isEmpty()) {
            return new ResponseEntity<List<Movie>>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<List<Movie>>(movieList, HttpStatus.OK);
    }

    //-------------Get Movies by Genre-----------------
    @RequestMapping(value = "movie/genre/{genreId}", method = RequestMethod.GET)
    public ResponseEntity<List<Movie>> getMoviesByGenre(@PathVariable(value = "genreId") long genreId) {
        List<Movie> movieList = movieService.getMoviesByGenre(genreId);
        if (movieList.isEmpty()) {
            return new ResponseEntity<List<Movie>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Movie>>(movieList, HttpStatus.OK);
    }
}
