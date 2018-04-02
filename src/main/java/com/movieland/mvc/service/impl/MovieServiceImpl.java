package com.movieland.mvc.service.impl;

import com.movieland.mvc.converter.CurrencyConverter;
import com.movieland.mvc.dao.GenreDao;
import com.movieland.mvc.dao.MovieDao;
import com.movieland.mvc.model.Genre;
import com.movieland.mvc.model.Movie;
import com.movieland.mvc.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieDao movieDao;

    private final GenreDao genreDao;

    @Autowired
    public MovieServiceImpl(MovieDao movieDao, GenreDao genreDao) {
        this.movieDao = movieDao;
        this.genreDao = genreDao;
    }

    @Override
    public Movie getMovieById(Long id) {
        return movieDao.getOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public void addMovie(Movie movie) {
        movieDao.save(movie);
    }

    @Override
    public void deleteMovie(Long id) {
        movieDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public void updateMovie(Movie movie) {
        movieDao.save(movie);
    }

    @Override
    public List<Movie> getThreeRandomMovies() {
        List<Movie> listWithRandomMovies = new ArrayList<>();
        Long randomTo = (long) (movieDao.findAll().size());

        for (int i = 0; i < 3; i++) {
            Long randomId = (long) (Math.random() * randomTo);
            listWithRandomMovies.add(movieDao.getOne(randomId));
        }
        return listWithRandomMovies;
    }

    @Override
    public List<Movie> getMoviesByGenre(Long id) {
        List<Genre> genreList;
        genreList = genreDao.findAll();

        for (Genre items : genreList) {
            if (items.getId() == id) {
                return items.getMovieGenreList();
            }
        }
        return null;
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieDao.findAll();
    }

    @Override
    public List<Movie> getAllMoviesSortedByRating() {
        List<Movie> movieList = movieDao.findAll();
        movieList.sort(Comparator.comparing(Movie::getRating));
        Collections.reverse(movieList);
        return movieList;
    }

    @Override
    public List<Movie> getAllMoviesSortedByAscPrice() {
        List<Movie> movieList = movieDao.findAll();
        movieList.sort(Comparator.comparing(Movie::getPrice));
        return movieList;
    }

    @Override
    public List<Movie> getAllMoviesSortedNyDescPrice() {
        List<Movie> movieList = movieDao.findAll();
        movieList.sort(Comparator.comparing(Movie::getPrice));
        Collections.reverse(movieList);
        return movieList;
    }

    @Override
    public Movie getMovieCurrencyConverted(Long movieId, String currency) {
        CurrencyConverter currencyConverter = new CurrencyConverter();
        if (currency.equals("USD")) {
            Movie movie = movieDao.getOne(movieId);
            double priceUA = movie.getPrice();
            double priceUSD = currencyConverter.usdConvert(priceUA);
            movie.setPrice(priceUSD);
            return movie;
        }
        return null;
    }
}