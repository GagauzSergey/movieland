package com.movieland.mvc.controller;

import com.movieland.mvc.model.Genre;
import com.movieland.mvc.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@CrossOrigin
public class RestGenreController {

    private GenreService genreService;

    @Autowired
    public RestGenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    //-------------  All Genres--------------------
    @RequestMapping(value = "genre", method = RequestMethod.GET)
    public ResponseEntity<List<Genre>> getAllGenres() {
        List<Genre> genres = genreService.getAllGenres();

        if (genres != null) {
            return new ResponseEntity<List<Genre>>(genres, HttpStatus.OK);
        }
        return new ResponseEntity<List<Genre>>(HttpStatus.NO_CONTENT);
    }
}
