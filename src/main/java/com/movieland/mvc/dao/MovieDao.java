package com.movieland.mvc.dao;

import com.movieland.mvc.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieDao extends JpaRepository <Movie, Long>{
}
