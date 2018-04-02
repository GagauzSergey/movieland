package com.movieland.mvc.service;

import com.movieland.mvc.model.Genre;

import java.util.List;

public interface GenreService {

    Genre getGenreById(Long id);

    void addGenre(Genre genre);

    void deleteGenre(Long id);

    void updateGenre(Genre genre);

    List<Genre> getAllGenres();

}
