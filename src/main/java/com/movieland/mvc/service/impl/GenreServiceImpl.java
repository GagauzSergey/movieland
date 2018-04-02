package com.movieland.mvc.service.impl;

import com.movieland.mvc.dao.GenreDao;
import com.movieland.mvc.model.Genre;
import com.movieland.mvc.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreDao genreDao;

    @Autowired
    public GenreServiceImpl(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    @Cacheable("genre")
    public Genre getGenreById(Long id) {
        return genreDao.getOne(id);
    }

    @Override
    public void addGenre(Genre genre) {
        genreDao.save(genre);
    }

    @Override
    public void deleteGenre(Long id) {
        genreDao.deleteById(id);
    }

    @Override
    public void updateGenre(Genre genre) {
        genreDao.save(genre);
    }

    @Override
    @Cacheable ("genre")
    public List<Genre> getAllGenres() {
        return genreDao.findAll();
    }
}
