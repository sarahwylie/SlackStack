package com.cooksys.cookslack.data.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AnnouncementPatchRequestDto {
    private String title;
    private String message;
}
