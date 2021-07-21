package com.example.footballleague.service;

import com.example.footballleague.dto.request.create.LeagueCreateRequestDTO;
import com.example.footballleague.dto.request.filter.LeagueFilterRequestDTO;
import com.example.footballleague.model.League;

import java.util.List;
import java.util.UUID;

public interface LeagueService {
    List<League> getLeagues(LeagueFilterRequestDTO leagueFilterRequestDTO);

    League getLeagueById(UUID leagueId);

    League createLeague(LeagueCreateRequestDTO dto);
}
