package com.cooksys.cookslack.data.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Team {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private Boolean deleted = false;
    @Column
    private String name;
    @Column
    private String description;
    @ManyToOne
    private Company company;
    @OneToMany(mappedBy = "team")
    @JsonIgnoreProperties("team")
    private List<User> users = new LinkedList<>();
}
