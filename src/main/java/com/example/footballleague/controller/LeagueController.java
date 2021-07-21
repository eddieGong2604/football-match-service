package com.example.footballleague.controller;

import com.example.footballleague.dto.request.create.LeagueCreateRequestDTO;
import com.example.footballleague.dto.request.filter.LeagueFilterRequestDTO;
import com.example.footballleague.dto.response.LeagueResponseDTO;
import com.example.footballleague.model.League;
import com.example.footballleague.service.LeagueService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/leagues")
@RequiredArgsConstructor
public class LeagueController {
    private final LeagueService leagueService;
    /*Need test*/

    @Operation(summary = "Get leagues based on search params. If none provided, return all.")
    @GetMapping
    public ResponseEntity<?> getLeagues(LeagueFilterRequestDTO leagueFilterRequestDTO) {
        List<League> leagues = leagueService.getLeagues(leagueFilterRequestDTO);
        return new ResponseEntity<>(leagues.stream()
                .map(LeagueResponseDTO::fromModel)
                .collect(Collectors.toList()), HttpStatus.OK);
    }
    /*Need test*/

    @Operation(summary = "Get league by id.")
    @GetMapping("/{leagueId}")
    public ResponseEntity<?> getLeagueById(@PathVariable("leagueId") UUID leagueId) {
        League league = leagueService.getLeagueById(leagueId);
        return new ResponseEntity<>(LeagueResponseDTO.fromModel(league), HttpStatus.OK);
    }
    /*Need test*/

    @Operation(summary = "Create league.")
    @PostMapping
    public ResponseEntity<?> createLeague(@RequestBody LeagueCreateRequestDTO dto) {
        League league = leagueService.createLeague(dto);
        return new ResponseEntity<>(LeagueResponseDTO.fromModel(league), HttpStatus.CREATED);
    }
}
