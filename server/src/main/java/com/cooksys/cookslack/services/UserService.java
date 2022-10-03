package com.cooksys.cookslack.services;

import com.cooksys.cookslack.data.dtos.*;
import java.util.List;


public interface UserService {

    List<UserResponseDto> getAllUsers();

    UserResponseDto getUserByUsername(String username);

    UserResponseDto createNewUser(UserRequestDto userToCreate);

    UserResponseDto deleteUser(String username);

    List<UserResponseDto> getAllUsersByTeam(Long teamID);

    UserResponseDto updateUser(String username, UserPatchRequestDto userPatchRequestDto);

    UserResponseDto addUserToTeam(String username, Long teamID);
}