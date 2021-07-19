package com.example.footballleague.service.impl;

import com.example.footballleague.dto.request.MatchFilterRequestDTO;
import com.example.footballleague.model.Match;
import com.example.footballleague.repository.MatchRepository;
import com.example.footballleague.service.MatchService;
import com.example.footballleague.specification.MatchSpecs;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
public class MatchServiceImpl implements MatchService {

    private final MatchRepository matchRepository;

    private final RedisTemplate<String, List<Match>> redisTemplate;

    @Override
    public List<Match> getMatches(MatchFilterRequestDTO matchFilter) {
        String redisKey = matchFilter.getRedisKey();
        List<Match> redisCache = redisTemplate.opsForValue().get(redisKey);
        if (redisCache != null) {
            return redisCache;
        }
        Specification<Match> conditions = Specification
                .where(MatchSpecs.inCountry(matchFilter.getCountryId()))
                .and(MatchSpecs.inSeason(matchFilter.getSeason()))
                .and(MatchSpecs.inLeague(matchFilter.getLeagueId()))
                .and(MatchSpecs.byTeam(matchFilter.getTeamId()));
        List<Match> matches = matchRepository.findAll(conditions);
        redisTemplate.opsForValue().set(redisKey, matches, 60, TimeUnit.SECONDS);
        return matches;
    }

}
