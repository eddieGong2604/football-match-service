package com.example.footballleague.controller;

import com.example.footballleague.dto.request.create.CountryCreateRequestDTO;
import com.example.footballleague.dto.request.filter.CountryFilterRequestDTO;
import com.example.footballleague.dto.response.CountryResponseDTO;
import com.example.footballleague.model.Country;
import com.example.footballleague.service.CountryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/countries")
@RequiredArgsConstructor
public class CountryController {
    private final CountryService countryService;


    @Operation(summary = "Get countries based on search params. If none provided, return all.")
    @GetMapping
    public ResponseEntity<?> getCountries(CountryFilterRequestDTO countryFilterRequestDTO) {
        List<Country> countryList = countryService.getCountries(countryFilterRequestDTO);
        return new ResponseEntity<>(countryList.stream()
                .map(CountryResponseDTO::fromModel)
                .collect(Collectors.toList()), HttpStatus.OK);
    }
    @Operation(summary = "Get country by id.")
    @GetMapping("/{countryId}")
    public ResponseEntity<?> getCountryById(@PathVariable UUID countryId) {
        Country country = countryService.getCountryById(countryId);
        return new ResponseEntity<>(CountryResponseDTO.fromModel(country), HttpStatus.OK);
    }

    @Operation(summary = "Create country.")
    @PostMapping
    public ResponseEntity<?> createCountry(@RequestBody CountryCreateRequestDTO dto) {
        Country country = countryService.createCountry(dto);
        return new ResponseEntity<>(CountryResponseDTO.fromModel(country), HttpStatus.OK);
    }

}
