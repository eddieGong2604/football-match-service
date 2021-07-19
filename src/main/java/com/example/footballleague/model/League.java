package com.example.footballleague.model;

import com.example.footballleague.model.base.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@Table(name = "league")
@Entity
@AllArgsConstructor
public class League extends BaseEntity {

    @Column(name = "leagueName", nullable = false)
    private String leagueName;

    @Column(name = "season", nullable = false)
    private String season;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @OneToMany(mappedBy = "league", fetch = FetchType.LAZY)
    private List<Team> teams;

    @OneToMany(mappedBy = "league", fetch = FetchType.LAZY)
    private List<Match> matches;

}
