package com.example.footballleague.service.impl;

import com.example.footballleague.dto.request.create.CountryCreateRequestDTO;
import com.example.footballleague.dto.request.filter.CountryFilterRequestDTO;
import com.example.footballleague.model.Country;
import com.example.footballleague.repository.CountryRepository;
import com.example.footballleague.service.CountryService;
import com.example.footballleague.specification.CountrySpecs;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    @Override
    public List<Country> getCountries(CountryFilterRequestDTO countryFilterRequestDTO) {
        Specification<Country> conditions = Specification
                .where(CountrySpecs.byCountryName(countryFilterRequestDTO.getCountryName()));
        return countryRepository.findAll(conditions);
    }

    @Override
    public Country createCountry(CountryCreateRequestDTO dto) {
        Country country = Country.builder().countryName(dto.getCountryName()).build();
        return countryRepository.save(country);
    }

    @Override
    public Country getCountryById(UUID countryId) {
        return countryRepository.findById(countryId).orElse(null);
    }
}
