package com.example.footballleague.model;

import com.example.footballleague.model.base.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@Entity
@Table(name = "country")
@NoArgsConstructor
@AllArgsConstructor
public class Country extends BaseEntity {

    @Column(name = "country_name", nullable = false)
    private String countryName;

    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    private List<League> leagues;

}
