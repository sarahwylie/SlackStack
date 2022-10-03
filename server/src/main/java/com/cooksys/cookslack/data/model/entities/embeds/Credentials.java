package com.cooksys.cookslack.data.model.entities.embeds;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
public class Credentials {
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column
    private Boolean admin = false;
}
