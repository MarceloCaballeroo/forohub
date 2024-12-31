package com.example.forohub.domain;

public class ValidationException extends  RuntimeException {
    public ValidationException(String mensaje) {
        super(mensaje);
    }
}