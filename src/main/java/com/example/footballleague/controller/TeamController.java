package com.example.footballleague.controller;

import com.example.footballleague.dto.request.create.TeamCreateRequestDTO;
import com.example.footballleague.dto.request.filter.TeamFilterRequestDTO;
import com.example.footballleague.dto.request.update.TeamUpdateRequestDTO;
import com.example.footballleague.dto.response.TeamResponseDTO;
import com.example.footballleague.model.Team;
import com.example.footballleague.service.TeamService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/teams")
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;

    @Operation(summary = "Get teams based on search params. If none provided, return all.")
    @GetMapping
    public ResponseEntity<?> getTeams(TeamFilterRequestDTO teamFilterRequestDTO) {
        List<Team> teams = teamService.getTeams(teamFilterRequestDTO);
        return new ResponseEntity<>(teams.stream()
                .map(TeamResponseDTO::fromModel)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @Operation(summary = "Get team by id.")
    @GetMapping("/{teamId}")
    public ResponseEntity<?> getTeamById(@PathVariable("teamId") UUID teamId) {
        Team team = teamService.getTeamById(teamId);
        return new ResponseEntity<>(TeamResponseDTO.fromModel(team), HttpStatus.OK);
    }

    @Operation(summary = "Create team.")
    @PostMapping
    public ResponseEntity<?> createTeam(@RequestBody TeamCreateRequestDTO dto) {
        Team team = teamService.createTeam(dto);
        return new ResponseEntity<>(TeamResponseDTO.fromModel(team), HttpStatus.CREATED);
    }

    @Operation(summary = "Update team by id.")
    @PutMapping("/{teamId}")
    public ResponseEntity<?> updateTeamById(@PathVariable UUID teamId, @RequestBody TeamUpdateRequestDTO dto) {
        Team team = teamService.updateTeamById(teamId, dto);
        return new ResponseEntity<>(TeamResponseDTO.fromModel(team), HttpStatus.OK);
    }
}
