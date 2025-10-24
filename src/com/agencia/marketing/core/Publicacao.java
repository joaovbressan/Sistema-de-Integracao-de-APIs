package com.agencia.marketing.core;

/**
 * Modelo de dados unificado para representar uma publicação feita.
 */
public class Publicacao {
    private String id;
    private String plataforma;
    private String url;

    public Publicacao(String id, String plataforma, String url) {
        this.id = id;
        this.plataforma = plataforma;
        this.url = url;
    }
    @Override
    public String toString() {
        return "Publicacao [id=" + id + ", plataforma=" + plataforma + ", url=" + url + "]";
    }
}