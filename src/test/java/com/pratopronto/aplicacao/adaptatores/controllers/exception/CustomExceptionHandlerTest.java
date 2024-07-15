package com.pratopronto.aplicacao.adaptatores.controllers.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CustomExceptionHandlerTest {

    private CustomExceptionHandler customExceptionHandler;

    @BeforeEach
    public void setUp() {
        customExceptionHandler = new CustomExceptionHandler();
    }

    @Test
    public void handleBadRequestException() {
        BadRequestException ex = new BadRequestException("Bad request");
        ResponseEntity<String> response = customExceptionHandler.handleCustomException(ex);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Bad request", response.getBody());
    }

    @Test
    public void handleNotFoundException() {
        NotFoundException ex = new NotFoundException("Not found");
        ResponseEntity<String> response = customExceptionHandler.handleCustomException(ex);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Not found", response.getBody());
    }

    @Test
    public void handleMethodArgumentNotValidException() {
        FieldError fieldError = new FieldError("objectName", "field", "default message");
        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
        when(ex.getFieldError()).thenReturn(fieldError);

        ResponseEntity<String> response = customExceptionHandler.handleCustomException(ex);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("default message", response.getBody());
    }
}
