package com.cooksys.cookslack.data.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Data
public class CredentialsRequestDto {

    @NotBlank
    @NonNull
    private String username;
    @NotBlank
    @NonNull
    private String password;

    private Boolean admin;
}
