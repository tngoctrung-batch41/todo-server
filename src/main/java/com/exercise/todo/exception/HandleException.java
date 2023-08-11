package com.exercise.todo.exception;

import com.exercise.todo.common.WrapResponseStatus;
import com.exercise.todo.response.WrapResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class HandleException extends ResponseEntityExceptionHandler {
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public WrapResponse resourceException(Exception ex){
        System.err.println("!!!!!!!!!!!!!!==> Handel error: " + ex.getMessage());
        return WrapResponse.error(WrapResponseStatus.INTERNAL_SERVER_ERROR,ex.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public WrapResponse resourceRuntimeException(RuntimeException ex){
        System.err.println("!!!!!!!!!!!!!!==> Handel error: " + ex.getMessage());
        return WrapResponse.error(WrapResponseStatus.SERVICE_ERROR,ex.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public WrapResponse resourceNotFoundException(NotFoundException ex){
        System.err.println("!!!!!!!!!!!!!!==> Handel error: " + ex.getMessage());
        return  WrapResponse.error(WrapResponseStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public WrapResponse resourceServiceException(ServiceException ex){
        System.err.println("!!!!!!!!!!!!!!==> Handel error: " + ex.getMessage());
        return  WrapResponse.error(WrapResponseStatus.SERVICE_ERROR, ex.getMessage());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        System.err.println("!!!!!!!!!!!!!!==> Handel error: " + ex.getMessage());
        return handleExceptionInternal(ex, WrapResponse.error(WrapResponseStatus.BAD_REQUEST,errors.toString()), headers,HttpStatus.BAD_REQUEST, request);
    }
}
