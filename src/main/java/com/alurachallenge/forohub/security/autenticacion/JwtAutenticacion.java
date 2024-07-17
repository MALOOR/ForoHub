package com.alurachallenge.forohub.security.autenticacion;

public class JwtAutenticacion {

    private String acesoToken;

    public JwtAutenticacion(String acesoToken) {
        this.acesoToken = acesoToken;
    }

    public String getAccessToken() {
        return acesoToken;
    }

    public void setAccessToken(String acesoToken) {
        this.acesoToken = acesoToken;
    }
}
