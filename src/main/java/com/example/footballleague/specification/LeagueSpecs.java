package com.example.footballleague.specification;

import com.example.footballleague.model.Country_;
import com.example.footballleague.model.League;
import com.example.footballleague.model.League_;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class LeagueSpecs {
    public static Specification<League> byLeagueName(String leagueName) {
        return (root, query, cb) -> {
            if (leagueName == null) {
                return cb.conjunction();
            }
            return cb.like(root.get(League_.leagueName).as(String.class), leagueName);
        };
    }

    public static Specification<League> bySeason(String season) {
        return (root, query, cb) -> {
            if (season == null) {
                return cb.conjunction();
            }
            return cb.equal(root.get(League_.SEASON).as(String.class), season);
        };
    }

    public static Specification<League> inCountry(UUID countryId) {
        return (root, query, cb) -> {
            if (countryId == null) {
                return cb.conjunction();
            }
            return cb.equal(root.get(League_.country).get(Country_.id), countryId);
        };
    }
}
