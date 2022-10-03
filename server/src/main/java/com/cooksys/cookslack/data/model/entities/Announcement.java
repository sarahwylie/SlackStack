package com.cooksys.cookslack.data.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Getter
@Setter
public class Announcement {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private final Timestamp date;
    @Column
    private Boolean deleted = false;
    @Column
    private String title;
    @Column
    private String message;
    @ManyToOne
    private Company company;
    @ManyToOne
    @JsonIgnoreProperties("company")
    private User author;

    public Announcement() {
        Instant now = Instant.now();
        date = Timestamp.from(now);
    }
}
