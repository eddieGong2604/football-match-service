package com.example.footballleague.dto.response;

import com.example.footballleague.model.League;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LeagueResponseDTO {

    private String id;
    private String leagueName;
    private CountryResponseDTO country;
    private String season;

    public static LeagueResponseDTO fromModel(League league) {
        return LeagueResponseDTO.builder()
                .id(league.getId().toString())
                .leagueName(league.getLeagueName())
                .country(CountryResponseDTO.fromModel(league.getCountry()))
                .season(league.getSeason())
                .build();
    }

}
