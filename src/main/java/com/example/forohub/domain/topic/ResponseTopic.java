package com.example.forohub.domain.topic;

import com.example.forohub.model.Topic;

import java.time.LocalDateTime;

public record ResponseTopic(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        StatusTopic status,
        String autor,
        String curso
) {
    public ResponseTopic(Topic topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                topico.getAutor().getUsername(),
                topico.getCurso()
        );
    }
}