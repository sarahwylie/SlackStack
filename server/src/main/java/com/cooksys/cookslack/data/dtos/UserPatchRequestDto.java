package com.cooksys.cookslack.data.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserPatchRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Boolean active;
    private String status;
    private CredentialsPatchRequestDto credentials;
}
