package com.example.footballleague.service;

import com.example.footballleague.dto.request.MatchFilterRequestDTO;
import com.example.footballleague.model.Match;

import java.util.List;

public interface MatchService {

    List<Match> getMatches(MatchFilterRequestDTO matchFilter);

}
