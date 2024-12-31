package com.example.forohub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topico, Long> {
    boolean existsByTituloAndMensaje(String titulo, String mensaje);
    List<Topico> findAll();

    boolean existsByTituloAndMensajeAndIdNot(String titulo, String mensaje, Long id);
}
