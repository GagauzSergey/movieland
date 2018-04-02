package com.movieland.mvc.service.impl;

import com.movieland.mvc.dao.CountryDao;
import com.movieland.mvc.model.Country;
import com.movieland.mvc.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryDao countryDao;

    @Autowired
    public CountryServiceImpl(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    @Override
    public Country getCountryById(Long id) {
        return countryDao.getOne(id);
    }

    @Override
    public void addCountry(Country country) {
        countryDao.save(country);
    }

    @Override
    public void deleteCountry(Long id) {
        countryDao.deleteById(id);
    }

    @Override
    public void updateCountry(Country country) {
        countryDao.save(country);
    }

    @Override
    public List<Country> getAllCountries() {
        return countryDao.findAll();
    }
}
