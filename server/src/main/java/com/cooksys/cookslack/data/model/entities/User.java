package com.cooksys.cookslack.data.model.entities;

import com.cooksys.cookslack.data.model.entities.embeds.Credentials;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "user_table")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private Boolean deleted = false;
    @Embedded
    private Credentials credentials;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String email;
    @Column
    private String phoneNumber;
    @Column
    private Boolean active = true;
    @Column
    private String status;
    @ManyToOne
    private Company company;
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
}
