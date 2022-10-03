package com.cooksys.cookslack.controllers;
import com.cooksys.cookslack.data.dtos.UserPatchRequestDto;
import com.cooksys.cookslack.data.dtos.UserRequestDto;
import com.cooksys.cookslack.data.dtos.UserResponseDto;
import lombok.RequiredArgsConstructor;
import com.cooksys.cookslack.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    public final UserService userService;

    @GetMapping
    public List<UserResponseDto> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{username}")
    public UserResponseDto getUserByUsername(@PathVariable String username){
        return userService.getUserByUsername(username);
    }

    @GetMapping("/team/{teamID}")
    public List<UserResponseDto> getAllUsersByTeam(@PathVariable long teamID){
        return userService.getAllUsersByTeam(teamID);
    }

    @PostMapping
    public UserResponseDto createNewUser(@RequestBody UserRequestDto userToCreate) {
        return userService.createNewUser(userToCreate);
    }

    @PatchMapping("/{username}/{teamId}")
    public UserResponseDto addUserToTeam(@PathVariable String username, @PathVariable Long teamId){
        return userService.addUserToTeam(username, teamId);
    }

    @PatchMapping("/{username}")
    public UserResponseDto updateUser(@PathVariable String username, @RequestBody UserPatchRequestDto userPatchRequestDto){
        return userService.updateUser(username, userPatchRequestDto);

    }

    @DeleteMapping("/{username}")
    public UserResponseDto deleteUser(@PathVariable String username){
        return userService.deleteUser(username);
    }

}