package com.cooksys.cookslack.data.model.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Company {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private Boolean deleted = false;
    @Column
    private String name;
    @Column
    private String description;
}
