package com.alurachallenge.forohub.service;

import com.alurachallenge.forohub.domain.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDetails checkCredentials(String username, String rawPassword) {
        return usuarioRepositorio.findByUsername(username);
    }

}
