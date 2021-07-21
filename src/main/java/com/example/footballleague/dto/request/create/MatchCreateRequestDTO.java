package com.example.footballleague.dto.request.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MatchCreateRequestDTO {
    private UUID homeTeamId;
    private UUID awayTeamId;
    private UUID leagueId;

    private String scoreOnHalfTime;
    private String scoreOnFullTime;
    private String penaltyScore;
    private String finalGameScore;
    private Integer numberOfRedCards;
    private Integer numberOfYellowCards;
    private Integer numberOfCorners;
    private Integer numberOfFaults;
    private Integer numberOfFreeKicks;
    private Integer numberOfPlayerExchanges;
}
