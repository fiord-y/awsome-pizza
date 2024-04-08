package com.fiordy.pizza.order.controller.advice;

import com.fiordy.pizza.order.exception.AbominationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExceptionHandlersTest {

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private ExceptionHandlers exceptionHandlers;

    @BeforeEach
    public void setUp() {
        bindingResult = mock(BindingResult.class);
        exceptionHandlers = new ExceptionHandlers();
    }

    @Test
    public void testHandleValidationExceptions() {
        // Mock
        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
        when(ex.getBindingResult()).thenReturn(bindingResult);
        when(bindingResult.getAllErrors()).thenReturn(
                List.of(new FieldError("objectName", "fieldName", "Error message")));

        ResponseEntity<Object> responseEntity = exceptionHandlers.handleValidationExceptions(ex);

        Map<String, String> expectedErrors = new HashMap<>();
        expectedErrors.put("fieldName", "Error message");
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(expectedErrors, responseEntity.getBody());
    }

    @Test
    public void testHandleAbominationExceptions() {
        // Mock
        AbominationException ex = mock(AbominationException.class);
        when(ex.getMessage()).thenReturn("Abomination message");

        ResponseEntity<Object> responseEntity = exceptionHandlers.handleAbominationExceptions(ex);

        Map<String, String> expectedErrors = Map.of("message", "Abomination message");
        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
        assertEquals(expectedErrors, responseEntity.getBody());
    }
}