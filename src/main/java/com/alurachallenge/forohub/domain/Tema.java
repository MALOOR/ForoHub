package com.alurachallenge.forohub.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


import java.time.LocalDateTime;

@Entity
public class Tema {


    @Column(nullable = false)
    @NotBlank
    private String title;

    @Column(nullable = false)
    @NotBlank
    private String content;

    @Column(nullable = false)
    @NotNull
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @NotNull
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    @NotBlank
    private String status;

    @Column(nullable = false)
    @NotBlank
    private String author;

    @Column(nullable = false)
    @NotBlank
    private String course;

}
