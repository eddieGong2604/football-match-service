package com.example.footballleague.repository;

import com.example.footballleague.model.League;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LeagueRepository extends JpaRepository<League, UUID>, JpaSpecificationExecutor<League> {

}
