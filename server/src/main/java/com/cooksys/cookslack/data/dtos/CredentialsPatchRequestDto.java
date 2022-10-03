package com.cooksys.cookslack.data.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CredentialsPatchRequestDto {
    private String username;
    private String password;
    private Boolean admin;
}
