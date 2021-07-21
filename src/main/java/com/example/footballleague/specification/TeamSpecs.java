package com.example.footballleague.specification;

import com.example.footballleague.model.League_;
import com.example.footballleague.model.Team;
import com.example.footballleague.model.Team_;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class TeamSpecs {

    public static Specification<Team> byTeamName(String teamName) {
        return (root, query, cb) -> {
            if (teamName == null) {
                return cb.conjunction();
            }
            return cb.like(root.get(Team_.teamName).as(String.class), teamName);
        };
    }

    public static Specification<Team> inLeague(UUID leagueId) {
        return (root, query, cb) -> {
            if (leagueId == null) {
                return cb.conjunction();
            }
            return cb.equal(root.get(Team_.league).get(League_.id).as(String.class), leagueId.toString());
        };
    }
}
