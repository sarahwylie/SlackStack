package com.cooksys.cookslack.data.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Data
public class AnnouncementRequestDto {
    @NotBlank
    @NonNull
    private String title;
    @NotBlank
    @NonNull
    private String message;
}
