package com.alurachallenge.forohub.controller;

import com.alurachallenge.forohub.domain.Tema;
import com.alurachallenge.forohub.domain.TemaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.Valid;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topico")
@Validated
public class TemaController {

    @Autowired
    private TemaRepositorio temaRepositorio;

    @GetMapping
    public List<Tema> getAllTopics() {
        return temaRepositorio.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tema> getTopicById(@PathVariable Long id) {
        return temaRepositorio.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Tema> createTopic(@Valid @RequestBody Tema tema) {
        // Verificar si ya existe un tópico con el mismo título y contenido
        boolean exists = temaRepositorio.existsByTitleAndContent(tema.getTitle(), tema.getContent());
        if (exists) {
            return ResponseEntity.status(409).build(); // 409 Conflict
        }

        tema.setCreatedAt(LocalDateTime.now());
        tema.setUpdatedAt(LocalDateTime.now());
        Tema guardaTema = temaRepositorio.save(tema);
        return ResponseEntity.ok(guardaTema);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Tema> updateTopic(@PathVariable Long id, @Valid @RequestBody Tema temaDetalle) {
        Optional<Tema> optionalTopic = temaRepositorio.findById(id);
        if (optionalTopic.isPresent()) {
            Tema tema = optionalTopic.get();

            // Hay que checar si existe otro tema con el mismo título y contenido
            boolean exists = temaRepositorio.existsByTitleAndContent(temaDetalle.getTitle(), temaDetalle.getContent());
            if (exists && (!tema.getTitle().equals(temaDetalle.getTitle()) || !tema.getContent().equals(temaDetalle.getContent()))) {
                return ResponseEntity.status(409).build(); // 409 Conflict
            }

            tema.setTitle(temaDetalle.getTitle());
            tema.setContent(temaDetalle.getContent());
            tema.setStatus(temaDetalle.getStatus());
            tema.setAuthor(temaDetalle.getAuthor());
            tema.setCourse(temaDetalle.getCourse());
            return ResponseEntity.ok(temaRepositorio.save(tema));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//    @DeleteMapping("/{id}")
//    @Transactional
//    public ResponseEntity<Object> deleteTopic(@PathVariable Long id) {
//        if (temaRepositorio.existsById(id)) {
//            temaRepositorio.deleteById(id);
//            return ResponseEntity.ok().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
    //Error en el codigo, resolver ya que marca error
}
