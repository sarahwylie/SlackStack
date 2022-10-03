package com.cooksys.cookslack.services;

import com.cooksys.cookslack.data.dtos.*;
import com.cooksys.cookslack.data.model.entities.*;

public interface UpdateService {

    public User makeUpdatedUser(User user, UserPatchRequestDto userPatchRequestDto);

    public Company makeUpdatedCompany(Company company, CompanyPatchRequestDto companyPatchRequestDto);

    public Announcement makeUpdatedAnnouncement(Announcement announcement, AnnouncementPatchRequestDto announcementPatchRequestDto);

    public Team makeUpdatedTeam(Team team, TeamPatchRequestDto teamPatchRequestDto, Long companyId);

    public Project makeUpdatedProject(Project project, ProjectPatchRequestDto projectPatchRequestDto, Long teamId);

}
