package com.example.footballleague.controller;

import com.example.footballleague.dto.request.create.MatchCreateRequestDTO;
import com.example.footballleague.dto.request.filter.MatchFilterRequestDTO;
import com.example.footballleague.model.Country;
import com.example.footballleague.model.League;
import com.example.footballleague.model.Match;
import com.example.footballleague.model.Team;
import com.example.footballleague.service.MatchService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MatchController.class)
public class MatchControllerTests {

    @MockBean
    MatchService matchService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getMatchesByCountryAndSeason() throws Exception {
        UUID countryId = UUID.randomUUID();
        String season = "Season 1";
        MatchFilterRequestDTO matchFilter = MatchFilterRequestDTO.builder()
                .countryId(countryId).season(season)
                .build();
        Mockito.when(matchService.getMatches(matchFilter)).thenReturn(
                Collections.singletonList(Match.builder()
                        .id(UUID.randomUUID())
                        .homeTeam(Team.builder().id(UUID.randomUUID()).teamName("Team 1").build())
                        .awayTeam(Team.builder().id(UUID.randomUUID()).teamName("Team 2").build())
                        .league(League.builder()
                                .id(UUID.randomUUID())
                                .leagueName("League 1")
                                .country(Country.builder()
                                        .id(countryId)
                                        .countryName("Vietnam")
                                        .build())
                                .season("Season 1")
                                .build())
                        .scoreOnHalfTime("1:1")
                        .scoreOnFullTime("1:1")
                        .penaltyScore("1:1")
                        .finalGameScore("1:1")
                        .numberOfRedCards(1)
                        .numberOfYellowCards(1)
                        .numberOfCorners(1)
                        .numberOfFaults(1)
                        .numberOfFreeKicks(1)
                        .numberOfPlayerExchanges(1)
                        .build())
        );
        mockMvc.perform(MockMvcRequestBuilders
                .get("/matches")
                .param("countryId", countryId.toString())
                .param("season", season)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].league.country.id", is(countryId.toString())))
                .andExpect(jsonPath("$[0].league.season", is(season)));
    }

    @Test
    public void getMatchesByLeagueAndSeason() throws Exception {
        UUID leagueId = UUID.randomUUID();
        String season = "Season 1";
        MatchFilterRequestDTO matchFilter = MatchFilterRequestDTO.builder()
                .leagueId(leagueId).season(season)
                .build();
        Mockito.when(matchService.getMatches(matchFilter)).thenReturn(
                Collections.singletonList(Match.builder()
                        .id(UUID.randomUUID())
                        .homeTeam(Team.builder().id(UUID.randomUUID()).teamName("Team 1").build())
                        .awayTeam(Team.builder().id(UUID.randomUUID()).teamName("Team 2").build())
                        .league(League.builder()
                                .id(leagueId)
                                .leagueName("League 1")
                                .country(Country.builder()
                                        .id(UUID.randomUUID())
                                        .countryName("Vietnam")
                                        .build())
                                .season("Season 1")
                                .build())
                        .scoreOnHalfTime("1:1")
                        .scoreOnFullTime("1:1")
                        .penaltyScore("1:1")
                        .finalGameScore("1:1")
                        .numberOfRedCards(1)
                        .numberOfYellowCards(1)
                        .numberOfCorners(1)
                        .numberOfFaults(1)
                        .numberOfFreeKicks(1)
                        .numberOfPlayerExchanges(1)
                        .build())
        );
        mockMvc.perform(MockMvcRequestBuilders
                .get("/matches")
                .param("leagueId", leagueId.toString())
                .param("season", season)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].league.id", is(leagueId.toString())))
                .andExpect(jsonPath("$[0].league.season", is(season)));
    }

    @Test
    public void getMatchesByTeamAndSeason() throws Exception {
        UUID teamId = UUID.randomUUID();
        String season = "Season 1";
        MatchFilterRequestDTO matchFilter = MatchFilterRequestDTO.builder()
                .teamId(teamId).season(season)
                .build();
        Mockito.when(matchService.getMatches(matchFilter)).thenReturn(
                Collections.singletonList(Match.builder()
                        .id(UUID.randomUUID())
                        .homeTeam(Team.builder().id(teamId).teamName("Team 1").build())
                        .awayTeam(Team.builder().id(UUID.randomUUID()).teamName("Team 2").build())
                        .league(League.builder()
                                .id(UUID.randomUUID())
                                .leagueName("League 1")
                                .country(Country.builder()
                                        .id(UUID.randomUUID())
                                        .countryName("Vietnam")
                                        .build())
                                .season("Season 1")
                                .build())
                        .scoreOnHalfTime("1:1")
                        .scoreOnFullTime("1:1")
                        .penaltyScore("1:1")
                        .finalGameScore("1:1")
                        .numberOfRedCards(1)
                        .numberOfYellowCards(1)
                        .numberOfCorners(1)
                        .numberOfFaults(1)
                        .numberOfFreeKicks(1)
                        .numberOfPlayerExchanges(1)
                        .build())
        );
        mockMvc.perform(MockMvcRequestBuilders
                .get("/matches")
                .param("teamId", teamId.toString())
                .param("season", season)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].homeTeam.id", is(teamId.toString())))
                .andExpect(jsonPath("$[0].league.season", is(season)));
    }


    @Test
    public void getMatchById() throws Exception {
        UUID matchId = UUID.randomUUID();
        Team homeTeam = Team.builder().teamName("Chelsea").id(UUID.randomUUID()).build();
        Team awayTeam = Team.builder().teamName("MU").id(UUID.randomUUID()).build();
        League league = League.builder().id(UUID.randomUUID()).leagueName("Premier League").country(Country.builder().countryName("England").id(UUID.randomUUID()).build()).season("2021/2022").build();
        String scoreOnHalfTime = "1-0";
        String scoreOnFullTime = "2-0";
        String penaltyScore = "0-0";
        String finalGameScore = "2-0";
        int numberOfRedCards = 0;
        int numberOfYellowCards = 1;
        int numberOfCorners = 2;
        int numberOfFaults = 3;
        int numberOfFreeKicks = 4;
        int numberOfPlayerExchanges = 5;

        when(matchService.getMatchById(matchId)).thenReturn(
                (Match.builder().id(matchId).homeTeam(homeTeam).awayTeam(awayTeam).league(league).scoreOnHalfTime(scoreOnHalfTime).scoreOnFullTime(scoreOnFullTime).penaltyScore(penaltyScore).finalGameScore(finalGameScore).numberOfRedCards(numberOfRedCards).numberOfYellowCards(numberOfYellowCards).numberOfCorners(numberOfCorners).numberOfFaults(numberOfFaults).numberOfFreeKicks(numberOfFreeKicks).numberOfPlayerExchanges(numberOfPlayerExchanges).build())
        );
        mockMvc.perform(MockMvcRequestBuilders
                .get("/matches/" + matchId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(matchId.toString())))
                .andExpect(jsonPath("$.homeTeam.id", is(homeTeam.getId().toString()))).andExpect(jsonPath("$.homeTeam.name", is(homeTeam.getTeamName()))).andExpect(jsonPath("$.awayTeam.id", is(awayTeam.getId().toString()))).andExpect(jsonPath("$.awayTeam.name", is(awayTeam.getTeamName()))).andExpect(jsonPath("$.league.id", is(league.getId().toString()))).andExpect(jsonPath("$.league.leagueName", is(league.getLeagueName()))).andExpect(jsonPath("$.league.country.id", is(league.getCountry().getId().toString()))).andExpect(jsonPath("$.league.country.countryName", is(league.getCountry().getCountryName()))).andExpect(jsonPath("$.league.season", is(league.getSeason()))).andExpect(jsonPath("$.scoreOnHalfTime", is(scoreOnHalfTime))).andExpect(jsonPath("$.scoreOnFullTime", is(scoreOnFullTime))).andExpect(jsonPath("$.penaltyScore", is(penaltyScore))).andExpect(jsonPath("$.finalGameScore", is(finalGameScore))).andExpect(jsonPath("$.numberOfRedCards", is(numberOfRedCards))).andExpect(jsonPath("$.numberOfYellowCards", is(numberOfYellowCards))).andExpect(jsonPath("$.numberOfCorners", is(numberOfCorners))).andExpect(jsonPath("$.numberOfFaults", is(numberOfFaults))).andExpect(jsonPath("$.numberOfFreeKicks", is(numberOfFreeKicks))).andExpect(jsonPath("$.numberOfPlayerExchanges", is(numberOfPlayerExchanges)));
    }

    @Test
    public void createMatch() throws Exception {
        UUID matchId = UUID.randomUUID();
        Team homeTeam = Team.builder().teamName("Chelsea").id(UUID.randomUUID()).build();
        Team awayTeam = Team.builder().teamName("MU").id(UUID.randomUUID()).build();
        League league = League.builder().id(UUID.randomUUID()).leagueName("Premier League").country(Country.builder().countryName("England").id(UUID.randomUUID()).build()).season("2021/2022").build();
        String scoreOnHalfTime = "1-0";
        String scoreOnFullTime = "2-0";
        String penaltyScore = "0-0";
        String finalGameScore = "2-0";
        int numberOfRedCards = 0;
        int numberOfYellowCards = 1;
        int numberOfCorners = 2;
        int numberOfFaults = 3;
        int numberOfFreeKicks = 4;
        int numberOfPlayerExchanges = 5;

        MatchCreateRequestDTO dto = MatchCreateRequestDTO.builder().homeTeamId(homeTeam.getId()).awayTeamId(awayTeam.getId()).leagueId(league.getId()).scoreOnHalfTime(scoreOnHalfTime).scoreOnFullTime(scoreOnFullTime).penaltyScore(penaltyScore).finalGameScore(finalGameScore).numberOfRedCards(numberOfRedCards).numberOfYellowCards(numberOfYellowCards).numberOfCorners(numberOfCorners).numberOfFaults(numberOfFaults).numberOfFreeKicks(numberOfFreeKicks).numberOfPlayerExchanges(numberOfPlayerExchanges).build();

        when(matchService.createMatch(dto)).thenReturn(
                (Match.builder().id(matchId).homeTeam(homeTeam).awayTeam(awayTeam).league(league).scoreOnHalfTime(scoreOnHalfTime).scoreOnFullTime(scoreOnFullTime).penaltyScore(penaltyScore).finalGameScore(finalGameScore).numberOfRedCards(numberOfRedCards).numberOfYellowCards(numberOfYellowCards).numberOfCorners(numberOfCorners).numberOfFaults(numberOfFaults).numberOfFreeKicks(numberOfFreeKicks).numberOfPlayerExchanges(numberOfPlayerExchanges).build())
        );
        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(MockMvcRequestBuilders
                .post("/matches")
                .content(mapper.writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(matchId.toString())))
                .andExpect(jsonPath("$.homeTeam.id", is(homeTeam.getId().toString()))).andExpect(jsonPath("$.homeTeam.name", is(homeTeam.getTeamName()))).andExpect(jsonPath("$.awayTeam.id", is(awayTeam.getId().toString()))).andExpect(jsonPath("$.awayTeam.name", is(awayTeam.getTeamName()))).andExpect(jsonPath("$.league.id", is(league.getId().toString()))).andExpect(jsonPath("$.league.leagueName", is(league.getLeagueName()))).andExpect(jsonPath("$.league.country.id", is(league.getCountry().getId().toString()))).andExpect(jsonPath("$.league.country.countryName", is(league.getCountry().getCountryName()))).andExpect(jsonPath("$.league.season", is(league.getSeason()))).andExpect(jsonPath("$.scoreOnHalfTime", is(scoreOnHalfTime))).andExpect(jsonPath("$.scoreOnFullTime", is(scoreOnFullTime))).andExpect(jsonPath("$.penaltyScore", is(penaltyScore))).andExpect(jsonPath("$.finalGameScore", is(finalGameScore))).andExpect(jsonPath("$.numberOfRedCards", is(numberOfRedCards))).andExpect(jsonPath("$.numberOfYellowCards", is(numberOfYellowCards))).andExpect(jsonPath("$.numberOfCorners", is(numberOfCorners))).andExpect(jsonPath("$.numberOfFaults", is(numberOfFaults))).andExpect(jsonPath("$.numberOfFreeKicks", is(numberOfFreeKicks))).andExpect(jsonPath("$.numberOfPlayerExchanges", is(numberOfPlayerExchanges)));
    }
}
