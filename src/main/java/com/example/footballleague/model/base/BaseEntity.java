package com.example.footballleague.model.base;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Data
@MappedSuperclass
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEntity implements Serializable {
    private static final long serialVersionUID = -5507740661014003787L;

//    @Id
//    @Column(name = "id", nullable = false)
//    @GeneratedValue(strategy= GenerationType.IDENTITY)
//    private Integer id;

    @Id
    @GenericGenerator(name = "UseExistingIdOtherwiseAutoGenerate",
            strategy = "com.example.footballleague.model.base.UseExistingIdOtherwiseAutoGenerate")
    @GeneratedValue(generator = "UseExistingIdOtherwiseAutoGenerate")
    @Column(unique = true, nullable = false)
    private UUID id;

    @NotNull
    @Column(name = "created_by")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)

    private UUID createdBy;

    @NotNull
    @Column(name = "created_on")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)

    private Timestamp createdOn;

    @NotNull
    @Column(name = "updated_by")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)

    private UUID updatedBy;

    @NotNull
    @Column(name = "updated_on")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)

    private Timestamp updatedOn;

    @NotNull
    @Column(name = "is_deleted")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)

    private Boolean isDeleted = false;


    @PrePersist
    protected void preparePersist() {
        //hardcoded
        createdBy = UUID.randomUUID();
        updatedBy = UUID.randomUUID();
        createdOn = Timestamp.from(Instant.now());
        updatedOn = Timestamp.from(Instant.now());
    }

    @PreUpdate
    protected void prepareUpdate() {
        //hardcoded
        updatedBy = UUID.randomUUID();
        updatedOn = Timestamp.from(Instant.now());
    }

}