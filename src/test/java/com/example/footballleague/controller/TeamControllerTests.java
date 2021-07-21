package com.example.footballleague.controller;

import com.example.footballleague.dto.request.create.TeamCreateRequestDTO;
import com.example.footballleague.dto.request.filter.LeagueFilterRequestDTO;
import com.example.footballleague.dto.request.filter.TeamFilterRequestDTO;
import com.example.footballleague.dto.request.update.TeamUpdateRequestDTO;
import com.example.footballleague.model.Country;
import com.example.footballleague.model.League;
import com.example.footballleague.model.Team;
import com.example.footballleague.service.TeamService;
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

@WebMvcTest(TeamController.class)
public class TeamControllerTests {

    @MockBean
    TeamService teamService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getTeams() throws Exception {
        String teamName = "Chelsea";
        UUID teamId = UUID.randomUUID();
        UUID leagueId = UUID.randomUUID();
        TeamFilterRequestDTO dto = TeamFilterRequestDTO.builder().teamName(teamName).leagueId(leagueId)
                .build();
        when(teamService.getTeams(dto)).thenReturn(Collections.singletonList(Team.builder().id(teamId).teamName(teamName).league(League.builder().id(leagueId).leagueName("Premier League").build()).build()));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/teams")
                .param("leagueId", leagueId.toString())
                .param("teamName", teamName)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(teamId.toString())))
                .andExpect(jsonPath("$[0].name", is(teamName)));
    }



    @Test
    public void getTeamById() throws Exception {
        String teamName = "Chelsea";
        UUID teamId = UUID.randomUUID();
        UUID leagueId = UUID.randomUUID();

        when(teamService.getTeamById(teamId)).thenReturn((Team.builder().id(teamId).teamName(teamName).league(League.builder().id(leagueId).leagueName("Premier League").build()).build()));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/teams/" + teamId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(teamId.toString())))
                .andExpect(jsonPath("$.name", is(teamName)));
    }

    @Test
    public void createTeam() throws Exception {
        String teamName = "Chelsea";
        UUID teamId = UUID.randomUUID();
        UUID leagueId = UUID.randomUUID();
        TeamCreateRequestDTO dto = TeamCreateRequestDTO.builder().teamName(teamName).leagueId(leagueId)
                .build();
        when(teamService.createTeam(dto)).thenReturn(Team.builder().id(teamId).teamName(teamName).league(League.builder().id(leagueId).leagueName("Premier League").build()).build());

        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(MockMvcRequestBuilders
                .post("/teams")
                .content(mapper.writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(teamId.toString())))
                .andExpect(jsonPath("$.name", is(teamName)));
    }


    @Test
    public void updateTeamById() throws Exception {
        UUID teamId = UUID.randomUUID();
        UUID leagueId = UUID.randomUUID();
        String newTeamName = "Chelsea 2";
        TeamUpdateRequestDTO dto = TeamUpdateRequestDTO.builder().teamName(newTeamName).build();
        when(teamService.updateTeamById(teamId, dto)).thenReturn(Team.builder().id(teamId).teamName(newTeamName).league(League.builder().id(leagueId).leagueName("Premier League").build()).build());

        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(MockMvcRequestBuilders
                .put("/teams/" + teamId).content(mapper.writeValueAsString(dto))
                .content(mapper.writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(teamId.toString())))
                .andExpect(jsonPath("$.name", is(newTeamName)));
    }



}
