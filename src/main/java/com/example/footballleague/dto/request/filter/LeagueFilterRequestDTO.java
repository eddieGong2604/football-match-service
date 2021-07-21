package com.example.footballleague.dto.request.filter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeagueFilterRequestDTO {
    private String leagueName;
    private String season;
    private UUID countryId;

}
