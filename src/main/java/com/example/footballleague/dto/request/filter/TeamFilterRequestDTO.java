package com.example.footballleague.dto.request.filter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamFilterRequestDTO {
    private String teamName;
    private UUID leagueId;
}
