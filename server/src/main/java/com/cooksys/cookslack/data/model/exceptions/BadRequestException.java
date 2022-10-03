package com.cooksys.cookslack.data.model.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BadRequestException extends RuntimeException {
    private static final long serialVersionUID = 6073322983512750275L;
    private String message;
}
