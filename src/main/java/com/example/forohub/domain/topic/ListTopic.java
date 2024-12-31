package com.example.forohub.domain.topic;

import com.example.forohub.model.Topic;
import java.time.LocalDateTime;

public record ListTopic(
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        StatusTopic status,
        String autor,
        String curso) {
    public ListTopic(Topic topico) {
        this(
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                topico.getAutor().getLogin(),
                topico.getCurso()
        );
    }
}