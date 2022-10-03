package com.cooksys.cookslack.services;

import com.cooksys.cookslack.data.dtos.*;
import com.cooksys.cookslack.data.model.exceptions.BadRequestException;
import com.cooksys.cookslack.data.model.exceptions.NotFoundException;

public interface ValidationService {

    boolean newUserValidate(UserRequestDto user) throws BadRequestException;

    boolean newCompanyValidate(CompanyRequestDto company) throws BadRequestException;

    boolean newAnnouncementValidate(AnnouncementRequestDto announcement) throws BadRequestException;

    boolean newTeamValidate(TeamRequestDto team, Long companyId) throws BadRequestException, NotFoundException;

    boolean newProjectValidate(ProjectRequestDto project, Long teamId) throws BadRequestException, NotFoundException;

}
