package com.example.footballleague.service;

import com.example.footballleague.dto.request.create.CountryCreateRequestDTO;
import com.example.footballleague.dto.request.filter.CountryFilterRequestDTO;
import com.example.footballleague.model.Country;

import java.util.List;
import java.util.UUID;

public interface CountryService {
    List<Country> getCountries(CountryFilterRequestDTO countryFilterRequestDTO);

    Country createCountry(CountryCreateRequestDTO dto);

    Country getCountryById(UUID countryId);
}
