package com.cooksys.cookslack.services.impl;

import com.cooksys.cookslack.data.dtos.*;
import com.cooksys.cookslack.data.model.entities.*;
import com.cooksys.cookslack.data.model.exceptions.NotFoundException;
import com.cooksys.cookslack.data.repositories.CompanyRepository;
import com.cooksys.cookslack.data.repositories.TeamRepository;
import com.cooksys.cookslack.services.UpdateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateServiceImpl implements UpdateService {

    private final CompanyRepository companyRepository;
    private final TeamRepository teamRepository;

    @Override
    public User makeUpdatedUser(User user, UserPatchRequestDto userPatchRequestDto) {

        // Credentials field(s) checks
        if (userPatchRequestDto.getCredentials() != null) {
            if (userPatchRequestDto.getCredentials().getUsername() != null)
                user.getCredentials().setUsername(userPatchRequestDto.getCredentials().getUsername());
            if (userPatchRequestDto.getCredentials().getPassword() != null)
                user.getCredentials().setPassword(userPatchRequestDto.getCredentials().getPassword());
        }

        // User parent fields checks
        if (userPatchRequestDto.getFirstName() != null)
            user.setFirstName(userPatchRequestDto.getFirstName());
        if (userPatchRequestDto.getLastName() != null)
            user.setLastName(userPatchRequestDto.getLastName());
        if (userPatchRequestDto.getEmail() != null)
            user.setEmail(userPatchRequestDto.getEmail());
        if (userPatchRequestDto.getPhoneNumber() != null)
            user.setPhoneNumber(userPatchRequestDto.getPhoneNumber());
        if (userPatchRequestDto.getActive() != null)
            user.setActive(userPatchRequestDto.getActive());
        if (userPatchRequestDto.getStatus() != null)
            user.setStatus(userPatchRequestDto.getStatus());

        return user;
    }

    @Override
    public Company makeUpdatedCompany(Company company, CompanyPatchRequestDto companyPatchRequestDto) {
        if (companyPatchRequestDto.getName() != null)
            company.setName(companyPatchRequestDto.getName());
        if (companyPatchRequestDto.getDescription() != null)
            company.setDescription(companyPatchRequestDto.getDescription());

        return company;
    }

    @Override
    public Announcement makeUpdatedAnnouncement(Announcement announcement, AnnouncementPatchRequestDto announcementPatchRequestDto) {
        if (announcementPatchRequestDto.getTitle() != null)
            announcement.setTitle(announcementPatchRequestDto.getTitle());
        if (announcementPatchRequestDto.getMessage() != null)
            announcement.setMessage(announcementPatchRequestDto.getMessage());

        return announcement;
    }

    @Override
    public Team makeUpdatedTeam(Team team, TeamPatchRequestDto teamPatchRequestDto, Long companyId) {

        if (teamPatchRequestDto.getName() != null)
            team.setName(teamPatchRequestDto.getName());
        if (teamPatchRequestDto.getDescription() != null)
            team.setDescription(teamPatchRequestDto.getDescription());

        // If the provided companyId param is different from the Team.company.id,
        // go get the provided company and update to the new Company
        if (!Objects.equals(team.getCompany().getId(), companyId)) {
            Optional<Company> providedCompany = companyRepository.findById(companyId);
            if (providedCompany.isEmpty())
                throw new NotFoundException("Updated company for team not found.");
            team.setCompany(providedCompany.get());
        }

        return team;
    }

    @Override
    public Project makeUpdatedProject(Project project, ProjectPatchRequestDto projectPatchRequestDto, Long teamId) {

        if (projectPatchRequestDto.getName() != null)
            project.setName(projectPatchRequestDto.getName());
        if (projectPatchRequestDto.getDescription() != null)
            project.setDescription(projectPatchRequestDto.getDescription());

        // If the provided teamId param is different from the Project.team.id,
        // go get the provided team and update to the new Team
        if (!Objects.equals(project.getTeam().getId(), teamId)) {
            Optional<Team> providedTeam = teamRepository.findById(teamId);
            if (providedTeam.isEmpty())
                throw new NotFoundException("Updated team for project not found.");
            project.setTeam(providedTeam.get());
        }

        return project;
    }
}
