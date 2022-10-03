package com.cooksys.cookslack.data.mappers;

import com.cooksys.cookslack.data.dtos.*;
import com.cooksys.cookslack.data.model.entities.Project;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ProjectMapper {

    Project requestDtoToEntity(ProjectRequestDto projectRequestDto);

    Project patchRequestToEntity(ProjectPatchRequestDto projectPatchRequestDto);

    ProjectRequestDto entityToRequestDto(Project project);

    ProjectResponseDto entityToResponseDto(Project project);

    List<ProjectResponseDto> entitiesToResponseDtos(List<Project> projects);

}
