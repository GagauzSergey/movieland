package com.movieland.mvc.service;

import com.movieland.mvc.model.Country;

import java.util.List;

public interface CountryService {

    Country getCountryById (Long id);

    void addCountry (Country country);

    void deleteCountry (Long id);

    void updateCountry (Country country);

    List <Country> getAllCountries();
}
