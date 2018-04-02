package com.movieland.mvc.dao;

import com.movieland.mvc.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreDao extends JpaRepository<Genre, Long> {

}
