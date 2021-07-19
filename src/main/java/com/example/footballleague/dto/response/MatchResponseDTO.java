package com.example.footballleague.dto.response;

import com.example.footballleague.model.Match;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MatchResponseDTO {

    private String id;
    private TeamResponseDTO homeTeam;
    private TeamResponseDTO awayTeam;
    private LeagueResponseDTO league;
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

    public static MatchResponseDTO fromModel(Match match) {
        return MatchResponseDTO.builder()
                .id(match.getId().toString())
                .homeTeam(TeamResponseDTO.fromModel(match.getHomeTeam()))
                .awayTeam(TeamResponseDTO.fromModel(match.getAwayTeam()))
                .league(LeagueResponseDTO.fromModel(match.getLeague()))
                .scoreOnHalfTime(match.getScoreOnHalfTime())
                .scoreOnFullTime(match.getScoreOnFullTime())
                .penaltyScore(match.getPenaltyScore())
                .finalGameScore(match.getFinalGameScore())
                .numberOfRedCards(match.getNumberOfRedCards())
                .numberOfYellowCards(match.getNumberOfYellowCards())
                .numberOfCorners(match.getNumberOfCorners())
                .numberOfFaults(match.getNumberOfFaults())
                .numberOfFreeKicks(match.getNumberOfFreeKicks())
                .numberOfPlayerExchanges(match.getNumberOfPlayerExchanges())
                .build();
    }

}
