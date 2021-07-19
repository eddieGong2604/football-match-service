package com.example.footballleague.dto.response;

import com.example.footballleague.model.Team;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeamResponseDTO {

    private String id;
    private String name;

    public static TeamResponseDTO fromModel(Team team) {
        return TeamResponseDTO.builder()
                .id(team.getId().toString())
                .name(team.getTeamName())
                .build();
    }

}
