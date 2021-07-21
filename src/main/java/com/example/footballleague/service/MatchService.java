package com.example.footballleague.service;

import com.example.footballleague.dto.request.create.MatchCreateRequestDTO;
import com.example.footballleague.dto.request.filter.MatchFilterRequestDTO;
import com.example.footballleague.model.Match;

import java.util.List;
import java.util.UUID;

public interface MatchService {

    List<Match> getMatches(MatchFilterRequestDTO matchFilter);

    Match getMatchById(UUID matchId);

    Match createMatch(MatchCreateRequestDTO dto);
}
