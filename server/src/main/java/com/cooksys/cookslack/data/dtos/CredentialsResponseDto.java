package com.cooksys.cookslack.data.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CredentialsResponseDto {
    private String username;
    private Boolean admin;
}
