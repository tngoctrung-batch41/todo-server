package com.exercise.todo.exception;

import lombok.Data;

@Data
public class ServiceException extends RuntimeException{
    private String message;
    public ServiceException(String message) {
        super();
        this.message=message;
    }
}
