package com.cooksys.cookslack.data.mappers;

import com.cooksys.cookslack.data.dtos.*;
import com.cooksys.cookslack.data.model.entities.Team;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface TeamMapper {

    Team requestDtoToEntity(TeamRequestDto teamRequestDto);

    Team patchRequestToEntity(TeamPatchRequestDto teamPatchRequestDto);

    TeamRequestDto entityToRequestDto(Team team);

    TeamResponseDto entityToResponseDto(Team team);

    List<TeamResponseDto> entitiesToResponseDtos(List<Team> teams);

}
