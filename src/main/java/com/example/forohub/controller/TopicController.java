package com.example.forohub.controller;

import com.example.forohub.domain.ValidationException;
import com.example.forohub.domain.topic.*;
import com.example.forohub.model.Topic;
import com.example.forohub.security.error.TopicNotFoundException;
import com.example.forohub.repository.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/topicos")
public class TopicController {
    @Autowired
    private TopicRepository topicoRepository;
    @Autowired
    private UserRepository usuarioRepository;
    @PostMapping
    public ResponseEntity<ResponseTopic> registrarTopico(
            @RequestBody @Valid RegisterTopic datosRegistroTopico,
            UriComponentsBuilder uriComponentsBuilder) {

        if (topicoRepository.existsByTituloAndMensaje(datosRegistroTopico.titulo(), datosRegistroTopico.mensaje())) {
            throw new ValidationException("Ya existe un tópico con este título y mensaje.");
        }

        Topic topico = new Topic(datosRegistroTopico);
        topicoRepository.save(topico);

        ResponseTopic datosRespuestaTopico = new ResponseTopic(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                topico.getAutor().getUsername(),
                topico.getCurso()
        );

        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaTopico);
    }

    @GetMapping
    public ResponseEntity<List<ListTopic>> listadoTopicos(@PageableDefault(size = 20) Pageable paginacion) {
        List<ListTopic> topicos = topicoRepository.findAll(paginacion)
                .map(ListTopic::new)
                .getContent();
        return ResponseEntity.ok(topicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> obtenerDetalleTopico(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().body("ID es requerido");
        }

        Topic topico = topicoRepository.findById(id)
                .orElseThrow(() -> new TopicNotFoundException("Tópico no encontrado con ID: " + id));
        ListTopic datosDetalleTopico = new ListTopic(topico);
        return ResponseEntity.ok(datosDetalleTopico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ListTopic> actualizarTopico(
            @PathVariable Long id,
            @Valid @RequestBody UpdateTopic datosActualizarTopico) {

        Optional<Topic> topicoOptional = topicoRepository.findById(id);
        if (topicoOptional.isEmpty()) {
            throw new TopicNotFoundException("Tópico no encontrado con ID: " + id);
        }
        Topic topico = topicoOptional.get();

        if (topicoRepository.existsByTituloAndMensajeAndIdNot(
                datosActualizarTopico.titulo(),
                datosActualizarTopico.mensaje(),
                id)) {
            throw new ValidationException("Ya existe un tópico con el mismo título y mensaje");
        }

        topico.setTitulo(datosActualizarTopico.titulo());
        topico.setMensaje(datosActualizarTopico.mensaje());
        topico.setStatus(datosActualizarTopico.status());
        topico.setAutor(datosActualizarTopico.autor());
        topico.setCurso(datosActualizarTopico.curso());

        topicoRepository.save(topico);

        return ResponseEntity.ok(new ListTopic(topico));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarTopico(@PathVariable Long id) {
        Optional<Topic> topicoOptional = topicoRepository.findById(id);

        if (topicoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Tópico no encontrado con ID: " + id);
        }

        topicoRepository.deleteById(id);

        return ResponseEntity.ok("Tópico eliminado con éxito con ID: " + id);
    }
}