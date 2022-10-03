package com.cooksys.cookslack.services;

import com.cooksys.cookslack.data.dtos.CredentialsRequestDto;
import com.cooksys.cookslack.data.dtos.CredentialsResponseDto;
import com.cooksys.cookslack.data.dtos.UserResponseDto;

public interface AuthService {

    //api healthCheck
    String healthCheck();

    //Authentication when user login
    UserResponseDto login(CredentialsRequestDto credentialsRequestDto);

    //Check Admin Status
    boolean checkAdmin(CredentialsRequestDto credentialsRequestDto);
}
