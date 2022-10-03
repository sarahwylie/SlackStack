package com.cooksys.cookslack.services.impl;

import com.cooksys.cookslack.data.dtos.CredentialsRequestDto;
import com.cooksys.cookslack.data.dtos.CredentialsResponseDto;
import com.cooksys.cookslack.data.dtos.UserResponseDto;
import com.cooksys.cookslack.data.mappers.CredentialsMapper;
import com.cooksys.cookslack.data.mappers.UserMapper;
import com.cooksys.cookslack.data.model.entities.User;
import com.cooksys.cookslack.data.model.entities.embeds.Credentials;
import com.cooksys.cookslack.data.model.exceptions.NotAuthorizedException;
import com.cooksys.cookslack.data.model.exceptions.NotFoundException;
import com.cooksys.cookslack.data.repositories.UserRepository;
import com.cooksys.cookslack.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserMapper userMapper;
    private final CredentialsMapper credentialsMapper;
    private final UserRepository userRepository;

    private DataSource ds;

    private User doesUserExist(CredentialsRequestDto credentialsRequestDto){
        Credentials userToFind = credentialsMapper.requestDtoToEntity(credentialsRequestDto);
        Optional<User> optionalUser = userRepository.findByCredentialsUsernameAndDeletedFalse(userToFind.getUsername());
        if(optionalUser.isEmpty()) {
            throw new NotFoundException("User does not exist!");
        }
        return optionalUser.get();
    }

    //******* END POINTS ***********************************************************************

    //check api
    @Override
    public String healthCheck(){
        return "backend is running on PORT 8080";
    }

    //Login user - check if user exists and correct password
    @Override
    public UserResponseDto login(CredentialsRequestDto credentialsRequestDto){
        User foundUser = doesUserExist(credentialsRequestDto);
        if(foundUser.getCredentials().getPassword().equals(credentialsRequestDto.getPassword())){
            return userMapper.entityToResponseDto(foundUser);
        } else {
            throw new NotAuthorizedException("Incorrect username or password.");
        }
    }

    //check user's admin status
    @Override
    public boolean checkAdmin(CredentialsRequestDto credentialsRequestDto){
        User foundUser = doesUserExist(credentialsRequestDto);
        boolean adminStatus = foundUser.getCredentials().getAdmin();
        return adminStatus;
    }
}