package com.example.forohub.domain.topic;

import src.main.java.com.example.forohub.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record RegisterTopic(
        @NotBlank(message = "El titulo no puede estar vacio")
        String titulo,
        @NotBlank(message = "El mensaje no puede estar vacio")
        String mensaje,
        @NotNull(message = "El status no puede ser nulo")
        StatusTopic status,
        @NotNull(message = "El autor no puede ser nulo")
        User autor,
        @NotBlank(message = "El curso no puede estar vacio")
        String curso
) {
    public LocalDateTime fecha_creacion() {
        return LocalDateTime.now();
    }
}
