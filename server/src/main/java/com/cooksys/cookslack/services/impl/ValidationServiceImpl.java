package com.cooksys.cookslack.services.impl;

import com.cooksys.cookslack.data.dtos.*;
import com.cooksys.cookslack.data.model.entities.Company;
import com.cooksys.cookslack.data.model.entities.Team;
import com.cooksys.cookslack.data.model.exceptions.BadRequestException;
import com.cooksys.cookslack.data.model.exceptions.NotFoundException;
import com.cooksys.cookslack.data.repositories.CompanyRepository;
import com.cooksys.cookslack.data.repositories.ProjectRepository;
import com.cooksys.cookslack.data.repositories.TeamRepository;
import com.cooksys.cookslack.data.repositories.UserRepository;
import com.cooksys.cookslack.services.ValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Slf4j
public class ValidationServiceImpl implements ValidationService {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final TeamRepository teamRepository;
    private final ProjectRepository projectRepository;

    @Override
    public boolean newUserValidate(UserRequestDto user) {

        // ---- Has valid username ---- See Error message for constraints
        Pattern usernamePattern = Pattern.compile("^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$");
        Matcher usernameMatcher = usernamePattern.matcher(user.getCredentials().getUsername());
        boolean usernameIsValid = usernameMatcher.matches();
        if (!usernameIsValid)
            throw new BadRequestException(
                    "Invalid username. Length must be 5-20 characters and " +
                            "may contain single dots (.) or underscores (_). " +
                            "A dot or underscore may not be the first or last character." +
                            " No other special characters are allowed.");

        // ---- Has valid email ----
        Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
        Matcher emailMatcher = emailPattern.matcher(user.getEmail());
        boolean emailIsValid = emailMatcher.matches();
        if (!emailIsValid)
            throw new BadRequestException(
                    "Email invalid. Please enter a valid email.");

        // ---- Has unique username (globally) ----
        boolean usernameExists = userRepository.
                existsUserByCredentialsUsernameAndDeletedFalse(
                        user.getCredentials().getUsername());
        if (usernameExists)
            throw new BadRequestException(
                    user.getCredentials().getUsername() +
                            " is already taken. Please choose another username to continue.");

        // ---- Has unique email (globally) ----
        boolean emailExists = userRepository.existsUserByEmailAndDeletedFalse(user.getEmail());
        if (emailExists)
            throw new BadRequestException(
                    user.getEmail() +
                            " is already in use. Please provide another email to continue.");

        // ---- Has valid password ---- See Error message for constraints
        Pattern passwordPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$");
        Matcher passwordMatcher = passwordPattern.matcher(user.getCredentials().getPassword());
        boolean passIsValid = passwordMatcher.matches();
        if (!passIsValid)
            throw new BadRequestException(
                    "Password must contain at least one digit [0-9]. " +
                            "Must contain at least one lowercase Latin character [a-z]. " +
                            "Must contain at least one uppercase Latin character [A-Z]. " +
                            "Must contain at least one special character like ! @ # & ( ). " +
                            "Must contain a length of at least 8 characters and a maximum of 20 characters.");

        // ---- Has valid first name, (no special characters, 1-2 words (allows optional middle name)) ----
        Pattern firstNamePattern = Pattern.compile("^([A-Z][a-z]*(\\s?)+){1,2}\\s*$");
        Matcher firstNameMatcher = firstNamePattern.matcher(user.getFirstName());
        boolean firstNameIsValid = firstNameMatcher.matches();
        if (!firstNameIsValid)
            throw new BadRequestException(
                    "Special characters not allowed in first name. May include middle name.");
        // ---- Has valid last name, (one word, no special characters) ----
        Pattern lastNamePattern = Pattern.compile("^([A-Za-z]*(\\s?)+)\\s*$");
        Matcher lastNameMatcher = lastNamePattern.matcher(user.getLastName());
        boolean lastNameIsValid = lastNameMatcher.matches();
        if (!lastNameIsValid)
            throw new BadRequestException(
                    "Special characters not allowed in last name. Last name must be one word.");

        // Has valid phone number (10-digit string) ----
        Pattern phoneNumberPattern = Pattern.compile("^\\d{10}$");
        Matcher phoneNumberMatcher = phoneNumberPattern.matcher(user.getPhoneNumber());
        boolean phoneNumberIsValid = phoneNumberMatcher.matches();
        if (!phoneNumberIsValid)
            throw new BadRequestException(
                    "Phone number invalid. Pattern: 1234567890");

        return true;
    }

    @Override
    public boolean newCompanyValidate(CompanyRequestDto company) {

        // ----- Has valid company name (<= 20 characters) -----
        boolean companyNameIsValid = company.getName().length() <= 20;
        if (!companyNameIsValid)
            throw new BadRequestException(
                    "Company name must be no more than 20 characters long.");

        // ----- Has unique company name (globally) -----
        boolean companyNameExists = companyRepository.existsByNameAndDeletedFalse(company.getName());
        if (companyNameExists)
            throw new BadRequestException(
                    company.getName() +
                            " already exists. Please choose another company name.");

        return true;
    }

    @Override
    public boolean newAnnouncementValidate(AnnouncementRequestDto announcement) {
        String title = announcement.getTitle();
        String message = announcement.getMessage();

        // ----- Has valid title (5-20 characters) -----
        boolean titleLengthIsValid = title.length() >= 5 && title.length() <= 20;
        if (!titleLengthIsValid)
            throw new BadRequestException(
                    "Announcement title must be 5 to 20 characters long.");

        // ----- Has valid message (20-255 characters) -----
        boolean messageLengthIsValid = message.length() >= 20 && message.length() <= 255;
        if (!messageLengthIsValid)
            throw new BadRequestException(
                    "Announcement message must be 20 to 255 characters long.");

        return true;
    }

    @Override
    public boolean newTeamValidate(TeamRequestDto team, Long companyId) {
        // Has valid team name (<= 20 characters)
        boolean teamNameIsValid = team.getName().length() <= 20;
        if (!teamNameIsValid)
            throw new BadRequestException(
                    "Team name must be no more than 20 characters long.");

        // Has unique team name (within company)
        Optional<Company> company = companyRepository.findById(companyId);
        if (company.isEmpty()) throw new NotFoundException("Provided Company not found.");

        boolean teamNameExistsInCompany =
                teamRepository.existsByNameAndCompanyAndDeletedFalse(team.getName(), company.get());
        if (teamNameExistsInCompany)
            throw new BadRequestException(
                    "Team '" + team.getName() + "' already exists within " + company.get().getName() +
                            ". Please choose another name or delete the duplicate team.");

        return true;
    }

    @Override
    public boolean newProjectValidate(ProjectRequestDto project, Long teamId) {
        // Has valid project name (5-20 characters)
        boolean projectNameIsValid = project.getName().length() >= 5 && project.getName().length() <= 20;
        if (!projectNameIsValid)
            throw new BadRequestException(
                    "Project name must be 5 to 20 characters long.");

        // Has project name unique to the team
        Optional<Team> team = teamRepository.findById(teamId);
        if (team.isEmpty()) throw new NotFoundException("Provided Team not found.");
        boolean projectNameExistsInTeam =
                projectRepository.existsByNameAndTeamAndDeletedFalse(project.getName(), team.get());
        if (projectNameExistsInTeam)
            throw new BadRequestException(
                    "Project '" + project.getName() + "' already exists in team '" + team.get().getName() +
                            "'. Please choose another name or delete the duplicate project.");

        return true;
    }

}
