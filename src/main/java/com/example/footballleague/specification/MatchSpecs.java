package com.example.footballleague.specification;

import com.example.footballleague.model.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class MatchSpecs {

    public static Specification<Match> inSeason(String season) {
        return (root, query, cb) -> {
            if (season == null) {
                return cb.conjunction();
            }
            return cb.equal(root.join(Match_.LEAGUE).get(League_.SEASON).as(String.class), season);
        };
    }

    public static Specification<Match> inCountry(UUID countryId) {
        return (root, query, cb) -> {
            if (countryId == null) {
                return cb.conjunction();
            }
            return cb.equal(root.join(Match_.LEAGUE).join(League_.COUNTRY).get(Country_.ID), countryId);
        };
    }

    public static Specification<Match> inLeague(UUID leagueId) {
        return (root, query, cb) -> {
            if (leagueId == null) {
                return cb.conjunction();
            }
            return cb.equal(root.join(Match_.LEAGUE).get(League_.ID), leagueId);
        };
    }

    public static Specification<Match> byTeam(UUID teamId) {
        return (root, query, cb) -> {
            if (teamId == null) {
                return cb.conjunction();
            }
            return cb.or(
                    cb.equal(root.get(Match_.homeTeam).get(Team_.ID), teamId),
                    cb.equal(root.get(Match_.awayTeam).get(Team_.ID), teamId)
            );
        };
    }

}
