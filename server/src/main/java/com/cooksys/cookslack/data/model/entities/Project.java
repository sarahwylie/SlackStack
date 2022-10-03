package com.cooksys.cookslack.data.model.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Project {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private Boolean deleted = false;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private Boolean active = true;
    @ManyToOne
    private Team team;
}
