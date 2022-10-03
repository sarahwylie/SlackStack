package com.cooksys.cookslack.data.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ProjectResponseDto {
    private Long id;
    private String name;
    private String description;
    private Boolean active;
    private TeamResponseDto team;
}
