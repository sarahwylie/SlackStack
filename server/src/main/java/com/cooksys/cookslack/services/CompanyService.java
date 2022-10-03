package com.cooksys.cookslack.services;

import com.cooksys.cookslack.data.dtos.*;

import java.util.List;

public interface CompanyService {

    //GET all companies
    List<CompanyResponseDto> getAllCompanies();

    //create a company
    CompanyResponseDto createCompany(CompanyRequestDto companyRequestDto);

    //GET company by ID
    CompanyResponseDto getCompanyById(Long id);

    //PATCH company by ID
    CompanyResponseDto updateCompany(Long id, CompanyPatchRequestDto companyPatchRequestDto);

    //DELETE company by ID
    CompanyResponseDto deleteCompany(Long companyId);

    //GET all announcements from a company by company ID
    List<AnnouncementResponseDto> getAnnouncementsByCompany(Long companyId);

    //create a new announcement for a company
    AnnouncementResponseDto createAnnouncement(Long companyId, Long authorId, AnnouncementRequestDto announcementRequestDto);

    //GET single announcement
    AnnouncementResponseDto getAnnouncementById(Long id, Long announcementId);

    //PATCH to update an announcement using company ID and announcement ID
    AnnouncementResponseDto updateAnnouncement(Long id, Long announcementId, AnnouncementPatchRequestDto announcementPatchRequestDto);

    //DELETE a specific announcement by company ID and announcement ID
    AnnouncementResponseDto deleteAnnouncementById(Long companyId, Long announcementId);

    //GET all teams from a company by company ID
    List<TeamResponseDto> getAllTeamsAtCompany(Long id);

    //POST to create a team using company ID
    TeamResponseDto createTeam(Long id, TeamRequestDto teamRequestDto);

    //GET single team by id from company using company ID
    TeamResponseDto getTeamById(Long companyId, Long teamId);

    //PATCH a team by team ID and company ID
    TeamResponseDto updateTeam(Long companyId, Long teamId, TeamPatchRequestDto teamPatchRequestDto);

    //DELETE a team using team ID and company ID
    TeamResponseDto deleteTeamById(Long companyId, Long teamId);

    //GET all projects from a specific company using company ID
    List<ProjectResponseDto> getAllProjectsAtCompany(Long companyId);

    //POST to create a project using company ID
    ProjectResponseDto createProject(Long companyId, Long teamId, ProjectRequestDto projectRequestDto);

    //GET a specific project using the project ID and the company ID
    ProjectResponseDto getProjectById(Long companyId, Long projectId);

    //PATCH a project using project ID and company ID
    ProjectResponseDto updateProject(Long teamId, Long projectId, ProjectPatchRequestDto projectPatchRequestDto);

    //DELETE a project using project ID and company ID
    ProjectResponseDto deleteProject(Long companyId, Long projectId);

}