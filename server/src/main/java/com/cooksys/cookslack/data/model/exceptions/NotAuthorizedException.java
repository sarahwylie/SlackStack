package com.cooksys.cookslack.data.model.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NotAuthorizedException extends RuntimeException {
    private static final long serialVersionUID = -1038504121613276117L;
    private String message;
}
