package com.example.u5w3d1.exception;

import java.util.UUID;

public class NotFoundException extends RuntimeException{
    public NotFoundException(long id){
        super("l'id "+ id + " non è stato trovato");
    }
    public NotFoundException(String message) {
        super(message);
    }
}
