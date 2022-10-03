package com.cooksys.cookslack.data.mappers;

import com.cooksys.cookslack.data.dtos.*;
import com.cooksys.cookslack.data.model.entities.Announcement;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface AnnouncementMapper {

    Announcement requestDtoToEntity(AnnouncementRequestDto announcementRequestDto);

    Announcement patchRequestToEntity(AnnouncementPatchRequestDto announcementPatchRequestDto);

    AnnouncementRequestDto entityToRequestDto(Announcement announcement);

    AnnouncementResponseDto entityToResponseDto(Announcement announcement);

    List<AnnouncementResponseDto> entitiesToResponseDtos(List<Announcement> announcements);

}
