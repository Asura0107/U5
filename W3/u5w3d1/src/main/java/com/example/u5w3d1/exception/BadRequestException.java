package com.example.u5w3d1.exception;

import lombok.Getter;
import org.springframework.validation.ObjectError;

import java.util.List;
@Getter
public class BadRequestException extends RuntimeException {
    private List<ObjectError> errorsList;
    public BadRequestException(String message){
        super(message);
    }
    public BadRequestException(List<ObjectError> errorsList) {
        super("Errore nel payload");
        this.errorsList = errorsList;
    }
}
