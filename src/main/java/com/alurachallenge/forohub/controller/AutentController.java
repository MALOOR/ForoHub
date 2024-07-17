package com.alurachallenge.forohub.controller;
import com.alurachallenge.forohub.security.autenticacion.JwtAutenticacion;
import com.alurachallenge.forohub.security.autenticacion.LogeoRequest;
import com.alurachallenge.forohub.security.logeo.JwtTokenProvider;
import com.alurachallenge.forohub.service.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AutentController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UsuarioServicio userService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LogeoRequest logeoRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            logeoRequest.getUsername(),
                            logeoRequest.getPassword()));

            String token = tokenProvider.generateToken(authentication.getName());
            return ResponseEntity.ok(new JwtAutenticacion(token));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}
