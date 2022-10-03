package com.cooksys.cookslack.controllers.advice;

import com.cooksys.cookslack.data.dtos.ErrorDto;
import com.cooksys.cookslack.data.model.exceptions.BadRequestException;
import com.cooksys.cookslack.data.model.exceptions.NotAuthorizedException;
import com.cooksys.cookslack.data.model.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice(basePackages = { "com.cooksys.cookslack.controllers" })
@ResponseBody
public class ControllersAdvice {
    /**
     * @param request
     * @param badRequestException
     * @return An ErrorDto containing the appropriate message
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public ErrorDto handleBadRequestException(HttpServletRequest request, BadRequestException badRequestException) {
        return new ErrorDto(badRequestException.getMessage());
    }

    /**
     * @param request
     * @param notAuthorizedException
     * @return An ErrorDto containing the appropriate message
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotAuthorizedException.class)
    public ErrorDto handleNotAuthorizedException(HttpServletRequest request, NotAuthorizedException notAuthorizedException) {
        return new ErrorDto(notAuthorizedException.getMessage());
    }

    /**
     * @param request
     * @param notFoundException
     * @return An ErrorDto containing the appropriate message
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ErrorDto handleNotFoundException(HttpServletRequest request, NotFoundException notFoundException) {
        return new ErrorDto(notFoundException.getMessage());
    }
}
