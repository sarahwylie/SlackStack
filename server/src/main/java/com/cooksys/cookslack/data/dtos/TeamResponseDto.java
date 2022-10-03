package com.cooksys.cookslack.data.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class TeamResponseDto {
    private Long id;
    private String name;
    private String description;
    private CompanyResponseDto company;
    private List<TeamMemberResponseDto> users;
}
