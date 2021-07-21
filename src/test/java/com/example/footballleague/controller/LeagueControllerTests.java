package com.example.footballleague.controller;

import com.example.footballleague.dto.request.create.LeagueCreateRequestDTO;
import com.example.footballleague.dto.request.filter.LeagueFilterRequestDTO;
import com.example.footballleague.model.Country;
import com.example.footballleague.model.League;
import com.example.footballleague.service.LeagueService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
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

@WebMvcTest(LeagueController.class)

public class LeagueControllerTests {
    @MockBean
    LeagueService leagueService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getLeagues() throws Exception {
        UUID countryId = UUID.randomUUID();
        String countryName = "England";
        String season = "2021/2022";
        String leagueName = "Premier League";
        UUID leagueId = UUID.randomUUID();
        LeagueFilterRequestDTO leagueFilterRequestDTO = LeagueFilterRequestDTO.builder()
                .countryId(countryId).season(season).leagueName(leagueName)
                .build();
        when(leagueService.getLeagues(leagueFilterRequestDTO)).thenReturn(Collections.singletonList(League.builder().leagueName(leagueName).country(Country.builder().id(countryId).countryName(countryName).build()).season(season).id(leagueId).build()));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/leagues")
                .param("countryId", countryId.toString())
                .param("season", season)
                .param("leagueName", leagueName)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(leagueId.toString())))
                .andExpect(jsonPath("$[0].leagueName", is(leagueName)))
                .andExpect(jsonPath("$[0].country.id", is(countryId.toString()))).andExpect(jsonPath("$[0].country.countryName", is(countryName))).andExpect(jsonPath("$[0].season", is(season)));
    }

    @Test
    public void getLeagueById() throws Exception {
        UUID leagueId = UUID.randomUUID();
        String leagueName = "Premier League";
        String season = "2021/2022";
        UUID countryId = UUID.randomUUID();
        String countryName = "England";
        when(leagueService.getLeagueById(leagueId)).thenReturn(League.builder().leagueName(leagueName).id(leagueId).country(Country.builder().id(countryId).countryName(countryName).build()).season(season).build());

        mockMvc.perform(MockMvcRequestBuilders
                .get("/leagues/" + leagueId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(leagueId.toString())))
                .andExpect(jsonPath("$.leagueName", is(leagueName))).andExpect(jsonPath("$.country.id", is(countryId.toString()))).andExpect(jsonPath("$.country.countryName", is(countryName))).andExpect(jsonPath("$.season", is(season)));
    }

    @Test
    public void createLeague() throws Exception {
        String countryName = "England";
        UUID countryId = UUID.randomUUID();
        String leagueName = "Premier League";
        String season = "2021/2022";
        UUID leagueId = UUID.randomUUID();
        LeagueCreateRequestDTO createRequestDTO = LeagueCreateRequestDTO.builder().leagueName(leagueName).season(season).countryId(countryId).build();
        when(leagueService.createLeague(createRequestDTO)).thenReturn(League.builder().leagueName(leagueName).id(leagueId).country(Country.builder().id(countryId).countryName(countryName).build()).season(season).build());
        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(MockMvcRequestBuilders.post("/leagues")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(createRequestDTO)))
                .andExpect(status().isCreated()).andExpect(jsonPath("$.id", is(leagueId.toString())))
                .andExpect(jsonPath("$.leagueName", is(leagueName))).andExpect(jsonPath("$.country.id", is(countryId.toString()))).andExpect(jsonPath("$.country.countryName",is(countryName))).andExpect(jsonPath("$.season", is(season)));


    }


}
