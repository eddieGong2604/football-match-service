package com.example.footballleague.controller;

import com.example.footballleague.dto.request.create.MatchCreateRequestDTO;
import com.example.footballleague.dto.request.filter.MatchFilterRequestDTO;
import com.example.footballleague.dto.response.MatchResponseDTO;
import com.example.footballleague.model.Match;
import com.example.footballleague.service.MatchService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/matches")
@RequiredArgsConstructor
public class MatchController {

    private final MatchService matchService;
    @Operation(summary = "Get matches based on search params. If none provided, return all.")
    @GetMapping
    public ResponseEntity<?> getMatches(MatchFilterRequestDTO matchFilterRequestDTO) {
        List<Match> matches = matchService.getMatches(matchFilterRequestDTO);
        return new ResponseEntity<>(matches.stream()
                .map(MatchResponseDTO::fromModel)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    /*Need test*/
    @Operation(summary = "Get match by id.")
    @GetMapping("/{matchId}")
    public ResponseEntity<?> getMatchById(@PathVariable("matchId") UUID matchId) {
        Match match = matchService.getMatchById(matchId);
        return new ResponseEntity<>(MatchResponseDTO.fromModel(match), HttpStatus.OK);
    }
    /*Need test*/

    @Operation(summary = "Create match.")
    @PostMapping
    public ResponseEntity<?> createMatch(@RequestBody MatchCreateRequestDTO dto) {
        Match match = matchService.createMatch(dto);
        return new ResponseEntity<>(MatchResponseDTO.fromModel(match), HttpStatus.OK);
    }
}

