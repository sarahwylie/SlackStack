package com.cooksys.cookslack.data.mappers;

import com.cooksys.cookslack.data.dtos.*;
import com.cooksys.cookslack.data.model.entities.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    User requestDtoToEntity(UserRequestDto userRequestDto);

    User patchRequestToEntity(UserPatchRequestDto userPatchRequestDto);

    UserRequestDto entityToRequestDto(User user);


    List<UserResponseDto> entitiesToResponseDtos(List<User> users);
    List<TeamMemberResponseDto> teamEntitiesToResponseDtos(List<User> users);

    /**
     * <code>Credentials</code> only contains <code>username</code> and <code>admin</code>, <code>password</code> is removed on the response.
     * @param user
     * @return a <code>UserResponseDto</code>
     */
    UserResponseDto entityToResponseDto(User user);

    TeamMemberResponseDto teamEntityToResponseDto(User user);

}
