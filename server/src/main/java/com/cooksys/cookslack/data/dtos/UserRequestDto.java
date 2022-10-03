package com.cooksys.cookslack.data.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Data
public class UserRequestDto {
    @NotBlank
    @NonNull
    private String firstName;
    @NotBlank
    @NonNull
    private String lastName;
    @NotBlank
    @NonNull
    private String email;
    @NonNull
    private String phoneNumber;

    private CredentialsRequestDto credentials;
}
