package com.example.forohub.repository;

import com.example.forohub.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    boolean existsByTituloAndMensaje(String titulo, String mensaje);
    List<Topic> findAll();

    boolean existsByTituloAndMensajeAndIdNot(String titulo, String mensaje, Long id);
}
