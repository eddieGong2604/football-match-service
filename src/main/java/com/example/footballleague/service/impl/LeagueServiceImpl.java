package com.example.footballleague.service.impl;

import com.example.footballleague.dto.request.create.LeagueCreateRequestDTO;
import com.example.footballleague.dto.request.filter.LeagueFilterRequestDTO;
import com.example.footballleague.model.Country;
import com.example.footballleague.model.League;
import com.example.footballleague.model.Team;
import com.example.footballleague.repository.CountryRepository;
import com.example.footballleague.repository.LeagueRepository;
import com.example.footballleague.repository.TeamRepository;
import com.example.footballleague.service.LeagueService;
import com.example.footballleague.specification.LeagueSpecs;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class LeagueServiceImpl implements LeagueService {
    private final LeagueRepository leagueRepository;
    private final CountryRepository countryRepository;
    private final TeamRepository teamRepository;

    @Override
    public List<League> getLeagues(LeagueFilterRequestDTO leagueFilterRequestDTO) {
        Specification<League> conditions = Specification
                .where(LeagueSpecs.byLeagueName(leagueFilterRequestDTO.getLeagueName())).and(LeagueSpecs.bySeason(leagueFilterRequestDTO.getSeason())).and(LeagueSpecs.inCountry(leagueFilterRequestDTO.getCountryId()));
        return leagueRepository.findAll(conditions);
    }

    @Override
    public League getLeagueById(UUID leagueId) {
        return leagueRepository.findById(leagueId).orElse(null);
    }

    @Override
    public League createLeague(LeagueCreateRequestDTO dto) {
        Country country = countryRepository.findById(dto.getCountryId()).orElse(null);
        League league = League.builder().leagueName(dto.getLeagueName()).season(dto.getSeason()).country(country).teams(new ArrayList<>()).build();
        return leagueRepository.save(league);
    }
}
