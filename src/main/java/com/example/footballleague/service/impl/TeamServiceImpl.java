package com.example.footballleague.service.impl;

import com.example.footballleague.dto.request.create.TeamCreateRequestDTO;
import com.example.footballleague.dto.request.filter.TeamFilterRequestDTO;
import com.example.footballleague.dto.request.update.TeamUpdateRequestDTO;
import com.example.footballleague.model.League;
import com.example.footballleague.model.Team;
import com.example.footballleague.repository.LeagueRepository;
import com.example.footballleague.repository.TeamRepository;
import com.example.footballleague.service.TeamService;
import com.example.footballleague.specification.TeamSpecs;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final LeagueRepository leagueRepository;

    @Override
    public List<Team> getTeams(TeamFilterRequestDTO teamFilterRequestDTO) {
        Specification<Team> conditions = Specification
                .where(TeamSpecs.byTeamName(teamFilterRequestDTO.getTeamName())).and(TeamSpecs.inLeague(teamFilterRequestDTO.getLeagueId()));
        return teamRepository.findAll(conditions);
    }

    @Override
    public Team getTeamById(UUID teamId) {
        return teamRepository.findById(teamId).orElse(null);
    }

    @Override
    public Team createTeam(TeamCreateRequestDTO dto) {
        League league = leagueRepository.findById(dto.getLeagueId()).orElse(null);
        Team team = Team.builder().teamName(dto.getTeamName()).league(league).build();
        return teamRepository.save(team);
    }

    @Override
    public Team updateTeamById(UUID teamId, TeamUpdateRequestDTO dto) {
        Team team = teamRepository.findById(teamId).orElse(null);
        team.setTeamName(dto.getTeamName());
        return teamRepository.save(team);
    }
}
