package com.example.footballleague.service.impl;

import com.example.footballleague.dto.request.create.MatchCreateRequestDTO;
import com.example.footballleague.dto.request.filter.MatchFilterRequestDTO;
import com.example.footballleague.model.League;
import com.example.footballleague.model.Match;
import com.example.footballleague.model.Team;
import com.example.footballleague.repository.LeagueRepository;
import com.example.footballleague.repository.MatchRepository;
import com.example.footballleague.repository.TeamRepository;
import com.example.footballleague.service.MatchService;
import com.example.footballleague.specification.MatchSpecs;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class MatchServiceImpl implements MatchService {

    private final MatchRepository matchRepository;
    private final TeamRepository teamRepository;
    private final LeagueRepository leagueRepository;

    @Override
    public List<Match> getMatches(MatchFilterRequestDTO matchFilter) {
        Specification<Match> conditions = Specification
                .where(MatchSpecs.inCountry(matchFilter.getCountryId()))
                .and(MatchSpecs.inSeason(matchFilter.getSeason()))
                .and(MatchSpecs.inLeague(matchFilter.getLeagueId()))
                .and(MatchSpecs.byTeam(matchFilter.getTeamId()));
        return matchRepository.findAll(conditions);
    }

    @Override
    public Match getMatchById(UUID matchId) {
        return matchRepository.findById(matchId).orElse(null);
    }

    @Override
    public Match createMatch(MatchCreateRequestDTO dto) {
        Team homeTeam = teamRepository.findById(dto.getHomeTeamId()).orElse(null);
        Team awayTeam = teamRepository.findById(dto.getAwayTeamId()).orElse(null);
        League league = leagueRepository.findById(dto.getLeagueId()).orElse(null);
        Match match = Match.builder().homeTeam(homeTeam).awayTeam(awayTeam).league(league).scoreOnHalfTime(dto.getScoreOnHalfTime()).scoreOnFullTime(dto.getScoreOnFullTime()).penaltyScore(dto.getPenaltyScore()).finalGameScore(dto.getFinalGameScore()).numberOfRedCards(dto.getNumberOfRedCards()).numberOfYellowCards(dto.getNumberOfYellowCards()).numberOfCorners(dto.getNumberOfCorners()).numberOfFaults(dto.getNumberOfFaults()).numberOfFreeKicks(dto.getNumberOfFreeKicks()).numberOfPlayerExchanges(dto.getNumberOfPlayerExchanges()).build();
        league.getMatches().add(match);
        return matchRepository.save(match);
    }

}
