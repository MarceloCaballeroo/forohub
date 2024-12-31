package com.example.forohub.security.error;

public class TopicNotFoundException extends RuntimeException {
    public TopicNotFoundException(String mensaje) {
        super(mensaje);
    }
}