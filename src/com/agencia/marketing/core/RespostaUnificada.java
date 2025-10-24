package com.agencia.marketing.core;

/**
 * TAREFA 2: Sistema de Resposta Unificado (com Generics)
 * Classe de resposta unificada que usa Generics.
 * Trata sucesso e erro de forma granular.
 * @param <T> O tipo de dado da resposta em caso de sucesso.
 */
public class RespostaUnificada<T> {
    private T dados;
    private String erro;
    private boolean sucesso;

    private RespostaUnificada(T dados, String erro, boolean sucesso) {
        this.dados = dados;
        this.erro = erro;
        this.sucesso = sucesso;
    }

    public static <T> RespostaUnificada<T> sucesso(T dados) {
        return new RespostaUnificada<>(dados, null, true);
    }

    public static <T> RespostaUnificada<T> falha(String mensagemErro) {
        return new RespostaUnificada<>(null, mensagemErro, false);
    }

    public boolean isSucesso() { return sucesso; }
    public T getDados() { return dados; }
    public String getErro() { return erro; }
}