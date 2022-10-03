package com.cooksys.cookslack.services.impl;

import com.cooksys.cookslack.data.dtos.*;
import com.cooksys.cookslack.data.mappers.AnnouncementMapper;
import com.cooksys.cookslack.data.mappers.CompanyMapper;
import com.cooksys.cookslack.data.mappers.ProjectMapper;
import com.cooksys.cookslack.data.mappers.TeamMapper;
import com.cooksys.cookslack.data.model.entities.*;
import com.cooksys.cookslack.data.model.exceptions.NotFoundException;
import com.cooksys.cookslack.data.repositories.*;
import com.cooksys.cookslack.services.CompanyService;
import com.cooksys.cookslack.services.UpdateService;
import com.cooksys.cookslack.services.ValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CompanyServiceImpl implements CompanyService {

    private final ValidationService validationService;
    private final UpdateService updateService;

    private final CompanyMapper companyMapper;
    private final AnnouncementMapper announcementMapper;
    private final TeamMapper teamMapper;
    private final ProjectMapper projectMapper;

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final AnnouncementRepository announcementRepository;
    private final TeamRepository teamRepository;
    private final ProjectRepository projectRepository;


    // GET all companies
    @Override
    public List<CompanyResponseDto> getAllCompanies() {
        return companyMapper.entitiesToResponseDtos(companyRepository.findAll());
    }

    //create a company
    @Override
    public CompanyResponseDto createCompany(CompanyRequestDto companyRequestDto) {
        validationService.newCompanyValidate(companyRequestDto);

        Company newCompany = companyMapper.requestDtoToEntity(companyRequestDto);
        Company savedCompany = companyRepository.saveAndFlush(newCompany);

        return companyMapper.entityToResponseDto(savedCompany);
    }

    //GET company by ID
    @Override
    public CompanyResponseDto getCompanyById(Long id) {
        Optional<Company> company = companyRepository.findById(id);
        if (company.isEmpty())
            throw new NotFoundException("Company not found.");
        return companyMapper.entityToResponseDto(company.get());
    }

    //PATCH company by ID
    @Override
    public CompanyResponseDto updateCompany(Long id, CompanyPatchRequestDto companyPatchRequestDto) {

        // Retrieve the Company to be updated from DB
        Optional<Company> company = companyRepository.findById(id);
        if (company.isEmpty())
            throw new NotFoundException("Company not found.");

        // Update the Company with whichever fields were included in the Patch request body
        Company updatedEntity = updateService.makeUpdatedCompany(company.get(), companyPatchRequestDto);

        // Validate the updated Company
        // (validator takes a requestDto, so convert it just for validation)
        validationService.newCompanyValidate(companyMapper.entityToRequestDto(updatedEntity));

        // Return the newly updated Company
        return companyMapper.entityToResponseDto(companyRepository.saveAndFlush(updatedEntity));
    }

    //DELETE company by ID
    @Override
    public CompanyResponseDto deleteCompany(Long companyId) {

        Optional<Company> company = companyRepository.findById(companyId);
        if (company.isEmpty())
            throw new NotFoundException("Company not found by id: " + companyId);

        Company foundCompany = company.get();

        foundCompany.setDeleted(true);

        return companyMapper.entityToResponseDto(
                companyRepository.saveAndFlush(foundCompany));
    }

    //GET all announcements from a company by company ID
    @Override
    public List<AnnouncementResponseDto> getAnnouncementsByCompany(Long companyId) {
        Optional<Company> parentCompany = companyRepository.findById(companyId);
        if (parentCompany.isEmpty())
            throw new NotFoundException("Company not found by id: " + companyId);
        List<Announcement> announcements = announcementRepository.findAllByCompanyAndDeletedFalse(parentCompany.get());
        if (announcements.isEmpty())
            throw new NotFoundException("No announcements were found for " + parentCompany.get().getName());
        return announcementMapper.entitiesToResponseDtos(announcements);
    }

    //create a new announcement for a company
    @Override
    public AnnouncementResponseDto createAnnouncement(Long companyId, Long authorId, AnnouncementRequestDto announcementRequestDto) {
        validationService.newAnnouncementValidate(announcementRequestDto);
        Announcement newAnnouncement = announcementMapper.requestDtoToEntity(announcementRequestDto);
        Optional<Company> company = companyRepository.findById(companyId);

        if (company.isEmpty()) throw new NotFoundException("Provided Company not found.");
        Optional<User> author = userRepository.findById(authorId);
        if (author.isEmpty()) throw new NotFoundException("Provided Author not found.");

        newAnnouncement.setCompany(company.get());
        newAnnouncement.setAuthor(author.get());

        Announcement savedAnnouncement = announcementRepository.saveAndFlush(newAnnouncement);
        return announcementMapper.entityToResponseDto(savedAnnouncement);
    }

    //GET single announcement
    @Override
    public AnnouncementResponseDto getAnnouncementById(Long id, Long announcementId) {
        Optional<Announcement> announcement = announcementRepository.findById(id);
        if (announcement.isEmpty())
            throw new NotFoundException("Announcement not found.");
        return announcementMapper.entityToResponseDto(announcement.get());
    }

    //PATCH to update an announcement using company ID and announcement ID
    @Override
    public AnnouncementResponseDto updateAnnouncement(Long id, Long announcementId, AnnouncementPatchRequestDto announcementPatchRequestDto) {

        // Retrieve the Announcement to be updated from DB
        Optional<Announcement> announcement = announcementRepository.findById(id);
        if (announcement.isEmpty())
            throw new NotFoundException("Announcement not found.");

        // Update the Announcement with whichever fields were included in the Patch request body
        Announcement updatedEntity = updateService.makeUpdatedAnnouncement(announcement.get(), announcementPatchRequestDto);

        // Validate the updated Announcement
        // (validator takes a requestDto, so convert it just for validation)
        validationService.newAnnouncementValidate(announcementMapper.entityToRequestDto(updatedEntity));

        // Return the newly updated Announcement
        return announcementMapper.entityToResponseDto(announcementRepository.saveAndFlush(updatedEntity));
    }

    //DELETE a specific announcement by company ID and announcement ID
    @Override
    public AnnouncementResponseDto deleteAnnouncementById(Long companyId, Long announcementId) {

        Optional<Announcement> announcement = announcementRepository.findById(announcementId);
        if (announcement.isEmpty())
            throw new NotFoundException("Announcement not found by id: " + announcementId);

        Announcement foundAnnouncement = announcement.get();

        foundAnnouncement.setDeleted(true);

        return announcementMapper.entityToResponseDto(
                announcementRepository.saveAndFlush(foundAnnouncement));
    }

    //GET all teams from a company by company ID
    @Override
    public List<TeamResponseDto> getAllTeamsAtCompany(Long companyId) {
        Optional<Company> company = companyRepository.findById(companyId);
        if (company.isEmpty())
            throw new NotFoundException("Company not found.");

        return teamMapper.entitiesToResponseDtos(
                teamRepository.findAllByCompanyAndDeletedFalse(company.get()));
    }

    //POST to create a team using company ID
    @Override
    public TeamResponseDto createTeam(Long companyId, TeamRequestDto teamRequestDto) {
        validationService.newTeamValidate(teamRequestDto, companyId);

        Team newTeam = teamMapper.requestDtoToEntity(teamRequestDto);
        Optional<Company> company = companyRepository.findById(companyId);
        if (company.isEmpty()) throw new NotFoundException("Provided Company not found.");

        newTeam.setCompany(company.get());

        Team savedTeam = teamRepository.saveAndFlush(newTeam);

        return teamMapper.entityToResponseDto(savedTeam);
    }

    //GET single team by id from company using company ID
    @Override
    public TeamResponseDto getTeamById(Long companyId, Long teamId) {
        Optional<Team> team = teamRepository.findById(teamId);
        if (team.isEmpty())
            throw new NotFoundException("Team not found.");
        return teamMapper.entityToResponseDto(team.get());
    }

    //PATCH a team by team ID and company ID
    @Override
    public TeamResponseDto updateTeam(Long companyId, Long teamId, TeamPatchRequestDto teamPatchRequestDto) {

        // Retrieve the Team to be updated from DB
        Optional<Team> team = teamRepository.findById(teamId);
        if (team.isEmpty())
            throw new NotFoundException("Team not found.");

        // Update the Team with whichever fields were included in the Patch request body/companyId Param
        Team updatedEntity = updateService.makeUpdatedTeam(team.get(), teamPatchRequestDto, companyId);

        // Validate the updated Team
        // (validator takes a requestDto, so convert it just for validation)
        validationService.newTeamValidate(teamMapper.entityToRequestDto(updatedEntity), companyId);

        // Return the newly updated Team
        return teamMapper.entityToResponseDto(teamRepository.saveAndFlush(updatedEntity));
    }

    //DELETE a team using team ID and company ID
    @Override
    public TeamResponseDto deleteTeamById(Long companyId, Long teamId) {

        Optional<Team> team = teamRepository.findById(teamId);
        if (team.isEmpty())
            throw new NotFoundException("Team not found by id: " + teamId);

        Team foundTeam = team.get();

        foundTeam.setDeleted(true);

        return teamMapper.entityToResponseDto(
                teamRepository.saveAndFlush(foundTeam));
    }

    //GET all projects from a specific company using company ID
    @Override
    public List<ProjectResponseDto> getAllProjectsAtCompany(Long companyId) {
        Optional<Company> company = companyRepository.findById(companyId);
        if (company.isEmpty())
            throw new NotFoundException("Company not found.");

        List<Team> companyTeams = teamRepository.findAllByCompanyAndDeletedFalse(company.get());

        List<Project> companyProjects = new ArrayList<>();
        for (Team t : companyTeams ) {
            List<Project> teamProjects =  projectRepository.findAllByTeamAndDeletedFalse(t);
            companyProjects.addAll(teamProjects);
        }

        return projectMapper.entitiesToResponseDtos(companyProjects);
    }

    //POST to create a project using company ID
    @Override
    public ProjectResponseDto createProject(Long companyId, Long teamId, ProjectRequestDto projectRequestDto) {
        validationService.newProjectValidate(projectRequestDto, teamId);

        Project newProject = projectMapper.requestDtoToEntity(projectRequestDto);
        Optional<Team> team = teamRepository.findById(teamId);
        if (team.isEmpty()) throw new NotFoundException("Provided Team not found.");

        newProject.setTeam(team.get());

        Project savedProject = projectRepository.saveAndFlush(newProject);

        return projectMapper.entityToResponseDto(savedProject);
    }

    //GET a specific project using the project ID and the company ID
    @Override
    public ProjectResponseDto getProjectById(Long companyId, Long projectId) {
        Optional<Project> project = projectRepository.findById(projectId);
        if (project.isEmpty())
            throw new NotFoundException("Project not found.");
        return projectMapper.entityToResponseDto(project.get());
    }

    //PATCH a project using project ID and company ID
    @Override
    public ProjectResponseDto updateProject(Long teamId, Long projectId, ProjectPatchRequestDto projectPatchRequestDto) {

        // Retrieve the Project to be updated from DB
        Optional<Project> project = projectRepository.findById(projectId);
        if (project.isEmpty())
            throw new NotFoundException("Project not found.");

        // Update the Project with whichever fields were included in the Patch request body
        Project updatedEntity = updateService.makeUpdatedProject(project.get(), projectPatchRequestDto, teamId);

        // Validate the updated Project
        // (validator takes a requestDto, so convert it just for validation)
        validationService.newProjectValidate(
                projectMapper.entityToRequestDto(updatedEntity), teamId);

        // Return the newly updated Project
        return projectMapper.entityToResponseDto(projectRepository.saveAndFlush(updatedEntity));
    }

    //DELETE a project using project ID and company ID
    @Override
    public ProjectResponseDto deleteProject(Long companyId, Long projectId) {

        Optional<Project> project = projectRepository.findById(projectId);
        if (project.isEmpty())
            throw new NotFoundException("Project not found by id: " + projectId);

        Project foundProject = project.get();

        foundProject.setDeleted(true);

        return projectMapper.entityToResponseDto(
                projectRepository.saveAndFlush(foundProject));
    }
}