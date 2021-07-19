package com.example.footballleague.dto.response;

import com.example.footballleague.model.Country;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CountryResponseDTO {

    private String id;
    private String countryName;

    public static CountryResponseDTO fromModel(Country country) {
        return CountryResponseDTO.builder()
                .id(country.getId().toString())
                .countryName(country.getCountryName())
                .build();
    }

}
