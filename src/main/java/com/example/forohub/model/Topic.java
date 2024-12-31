package com.example.forohub.model;

import com.example.forohub.domain.topic.*;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "topico")
@Entity(name = "Topico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    @Enumerated(EnumType.STRING)
    private StatusTopic status;
    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    private User autor;
    private String curso;

    public Topic(RegisterTopic datos) {
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.fechaCreacion = datos.fecha_creacion();
        this.status = datos.status() != null ? datos.status() : StatusTopic.NO_RESPONDIDO;
        this.autor = datos.autor();
        this.curso = datos.curso();
    }
}