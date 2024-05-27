package com.example.demo.service;

import com.example.demo.entities.Country;
import com.example.demo.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CountryService {
    private final CountryRepository countryRepository;

    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    public Country getCountryById(Integer id) {
        if (countryRepository.findById(id).isPresent()) {
            return countryRepository.findById(id).get();
        }
        return null;
    }
}
