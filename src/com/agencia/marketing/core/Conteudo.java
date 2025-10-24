package com.agencia.marketing.core;

/**
 * Modelo de dados unificado para o conteúdo a ser postado.
 */
public class Conteudo {
    private String titulo;
    private String textoPrincipal;
    private byte[] midia; // Para fotos/vídeos

    // Construtores, Getters e Setters
    public Conteudo(String textoPrincipal) {
        this.textoPrincipal = textoPrincipal;
    }
    public Conteudo(String titulo, String textoPrincipal) {
        this.titulo = titulo;
        this.textoPrincipal = textoPrincipal;
    }
    public Conteudo(String textoPrincipal, byte[] midia) {
        this.textoPrincipal = textoPrincipal;
        this.midia = midia;
    }

    public String getTitulo() { return titulo; }
    public String getTextoPrincipal() { return textoPrincipal; }
    public byte[] getMidia() { return midia; }
}