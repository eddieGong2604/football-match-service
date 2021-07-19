package com.example.footballleague.controller;

import com.example.footballleague.dto.request.MatchFilterRequestDTO;
import com.example.footballleague.model.Country;
import com.example.footballleague.model.League;
import com.example.footballleague.model.Match;
import com.example.footballleague.model.Team;
import com.example.footballleague.service.MatchService;
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
        String countryId = UUID.randomUUID().toString();
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
                                        .id(UUID.fromString(countryId))
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
                .param("countryId", countryId)
                .param("season", season)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].league.country.id", is(countryId)))
                .andExpect(jsonPath("$[0].league.season", is(season)));
    }

    @Test
    public void getMatchesByLeagueAndSeason() throws Exception {
        String leagueId = UUID.randomUUID().toString();
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
                                .id(UUID.fromString(leagueId))
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
                .param("leagueId", leagueId)
                .param("season", season)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].league.id", is(leagueId)))
                .andExpect(jsonPath("$[0].league.season", is(season)));
    }

    @Test
    public void getMatchesByTeamAndSeason() throws Exception {
        String teamId = UUID.randomUUID().toString();
        String season = "Season 1";
        MatchFilterRequestDTO matchFilter = MatchFilterRequestDTO.builder()
                .teamId(teamId).season(season)
                .build();
        Mockito.when(matchService.getMatches(matchFilter)).thenReturn(
                Collections.singletonList(Match.builder()
                        .id(UUID.randomUUID())
                        .homeTeam(Team.builder().id(UUID.fromString(teamId)).teamName("Team 1").build())
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
                .param("teamId", teamId)
                .param("season", season)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].homeTeam.id", is(teamId)))
                .andExpect(jsonPath("$[0].league.season", is(season)));
    }

}
