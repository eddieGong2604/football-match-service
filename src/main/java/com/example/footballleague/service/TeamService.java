package com.example.footballleague.service;

import com.example.footballleague.dto.request.create.TeamCreateRequestDTO;
import com.example.footballleague.dto.request.filter.TeamFilterRequestDTO;
import com.example.footballleague.dto.request.update.TeamUpdateRequestDTO;
import com.example.footballleague.model.Team;

import java.util.List;
import java.util.UUID;

public interface TeamService {
    List<Team> getTeams(TeamFilterRequestDTO teamFilterRequestDTO);

    Team getTeamById(UUID teamId);

    Team createTeam(TeamCreateRequestDTO dto);

    Team updateTeamById(UUID teamId, TeamUpdateRequestDTO dto);
}
