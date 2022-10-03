package com.cooksys.cookslack.controllers;

import com.cooksys.cookslack.data.dtos.*;
import com.cooksys.cookslack.services.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins="http://localhost:3000")
@RequiredArgsConstructor
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping
    public List<CompanyResponseDto> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyResponseDto createCompany(@RequestBody CompanyRequestDto companyRequestDto) {
        return companyService.createCompany(companyRequestDto);
    }

    @GetMapping("/{companyId}")
    public CompanyResponseDto getCompanyById(@PathVariable Long companyId) {
        return companyService.getCompanyById(companyId);
    }

    @PatchMapping("/{companyId}")
    public CompanyResponseDto updateCompany(@PathVariable Long companyId, @RequestBody CompanyPatchRequestDto companyPatchRequestDto) {
        return companyService.updateCompany(companyId, companyPatchRequestDto);
    }

    @DeleteMapping("/{companyId}")
    public CompanyResponseDto deleteCompany(@PathVariable Long companyId) {
        return companyService.deleteCompany(companyId);
    }

    // get all announcements from a company with the given id
    @GetMapping("/{companyId}/announcements")
    public List<AnnouncementResponseDto> getAnnouncementsByCompany(@PathVariable Long companyId) {
        return companyService.getAnnouncementsByCompany(companyId);
    }

    // post new announcement for a company, with an author
    // FIXME: Update endpoint in Wiki. Previously was: POST /{companyId}/announcements
    @PostMapping("/{companyId}/users/{authorId}/announcements")
    @ResponseStatus(HttpStatus.CREATED)
    public AnnouncementResponseDto createAnnouncement(
            @PathVariable Long companyId,
            @PathVariable Long authorId,
            @RequestBody AnnouncementRequestDto announcementRequestDto
    ) {
        return companyService.createAnnouncement(companyId, authorId, announcementRequestDto);
    }

    @GetMapping("/{companyId}/announcements/{announcementId}")
    public AnnouncementResponseDto getAnnouncementById(@PathVariable Long companyId, @PathVariable Long announcementId) {
        return companyService.getAnnouncementById(companyId, announcementId);
    }

    @PatchMapping("/{companyId}/announcements/{announcementId}")
    public AnnouncementResponseDto updateAnnouncement(
            @PathVariable Long companyId,
            @PathVariable Long announcementId,
            @RequestBody AnnouncementPatchRequestDto announcementPatchRequestDto
    ) {
        return companyService.updateAnnouncement(companyId, announcementId, announcementPatchRequestDto);
    }

    @DeleteMapping("/{companyId}/announcements/{announcementId}")
    public AnnouncementResponseDto deleteAnnouncementById(@PathVariable Long companyId, @PathVariable Long announcementId) {
        return companyService.deleteAnnouncementById(companyId, announcementId);
    }

    @GetMapping("/{companyId}/teams")
    public List<TeamResponseDto> getAllTeamsAtCompany(@PathVariable Long companyId) {
        return companyService.getAllTeamsAtCompany(companyId);
    }

    @PostMapping("/{companyId}/teams")
    @ResponseStatus(HttpStatus.CREATED)
    public TeamResponseDto createTeam(@PathVariable Long companyId, @RequestBody TeamRequestDto teamRequestDto) {
        return companyService.createTeam(companyId, teamRequestDto);
    }

    @GetMapping("/{companyId}/teams/{teamId}")
    public TeamResponseDto getTeamById(@PathVariable Long companyId, @PathVariable Long teamId) {
        return companyService.getTeamById(companyId, teamId);
    }

    @PatchMapping("/{companyId}/teams/{teamId}")
    public TeamResponseDto updateTeam(
            @PathVariable Long companyId,
            @PathVariable Long teamId,
            @RequestBody TeamPatchRequestDto teamPatchRequestDto
    ) {
        return companyService.updateTeam(companyId, teamId, teamPatchRequestDto);
    }

    @DeleteMapping("/{companyId}/teams/{teamId}")
    public TeamResponseDto deleteTeamById(@PathVariable Long companyId, @PathVariable Long teamId) {
        return companyService.deleteTeamById(companyId, teamId);
    }

    @GetMapping("/{companyId}/projects")
    public List<ProjectResponseDto> getAllProjectsAtCompany(@PathVariable Long companyId) {
        return companyService.getAllProjectsAtCompany(companyId);
    }

    // FIXME: Update endpoint in Wiki. Previously was: POST /{companyId}/projects
    @PostMapping("/{companyId}/teams/{teamId}/projects")
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectResponseDto createProject(
            @PathVariable Long teamId,
            @PathVariable Long companyId,
            @RequestBody ProjectRequestDto projectRequestDto
    ) {
        return companyService.createProject(companyId, teamId, projectRequestDto);
    }

    @GetMapping("/{companyId}/projects/{projectId}")
    public ProjectResponseDto getProjectById(@PathVariable Long companyId, @PathVariable Long projectId) {
        return companyService.getProjectById(companyId, projectId);
    }

    // FIXME: Update endpoint in Wiki. Previously was: PATCH /companies/{companyId}/projects/{projectId}
    @PatchMapping("/{companyId}/teams/{teamId}/projects/{projectId}")
    public ProjectResponseDto updateProject(
            @PathVariable Long teamId,
            @PathVariable Long projectId,
            @RequestBody ProjectPatchRequestDto projectPatchRequestDto) {
        return companyService.updateProject(teamId, projectId, projectPatchRequestDto);
    }

    // delete project
    @DeleteMapping("/{companyId}/projects/{projectId}")
    public ProjectResponseDto deleteProject(@PathVariable Long companyId, @PathVariable Long projectId) {
        return companyService.deleteProject(companyId, projectId);
    }
}