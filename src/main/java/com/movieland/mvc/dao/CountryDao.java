package com.movieland.mvc.dao;

import com.movieland.mvc.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryDao extends JpaRepository <Country, Long> {
}
