package com.agencia.marketing.core;

/**
 * DICA 1: Interface Unificada (Alvo)
 * Esta é a interface que o nosso sistema espera, similar à 'ProcessadorPagamento' do exemplo.
 */
public interface GerenciadorMidiaSocial {
    /**
     * Posta um conteúdo genérico na plataforma.
     * @param conteudo O conteúdo a ser postado.
     * @return Uma RespostaUnificada contendo a Publicacao ou um erro.
     */
    RespostaUnificada<Publicacao> postarConteudo(Conteudo conteudo);

    /**
     * Obtém estatísticas de uma postagem.
     * @param idPost A ID da postagem.
     * @return Uma RespostaUnificada contendo um objeto de estatísticas (aqui, um Integer) ou um erro.
     */
    RespostaUnificada<Integer> obterEstatisticas(String idPost);
}