package com.alurachallenge.forohub.domain;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemaRepositorio extends JpaRepository<Tema, Long> {

    boolean existsByTitleAndContent(String title, String content);
}
