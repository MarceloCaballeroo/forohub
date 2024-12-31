package com.example.forohub.domain.topic;

import com.example.forohub.model.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateTopic(
        @NotBlank String titulo,
        @NotBlank String mensaje,
        @NotNull StatusTopic status,
        @NotNull User autor,
        @NotBlank String curso) {
}
