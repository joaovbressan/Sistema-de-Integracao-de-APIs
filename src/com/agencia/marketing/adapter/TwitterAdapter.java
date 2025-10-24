package com.agencia.marketing.adapter;

import com.agencia.marketing.api.TwitterAPI;
import com.agencia.marketing.core.Conteudo;
import com.agencia.marketing.core.GerenciadorMidiaSocial;
import com.agencia.marketing.core.Publicacao;
import com.agencia.marketing.core.RespostaUnificada;

/**
 * TAREFA 1: Adapter para a TwitterAPI.
 * Ele "traduz" a chamada unificada 'postarConteudo' para a chamada específica 'tweet'.
 */
public class TwitterAdapter implements GerenciadorMidiaSocial {
    private final TwitterAPI twitterAPI; // Composição
    private final String token;

    public TwitterAdapter(TwitterAPI twitterAPI, String token) {
        this.twitterAPI = twitterAPI;
        this.token = token;
    }

    @Override
    public synchronized RespostaUnificada<Publicacao> postarConteudo(Conteudo conteudo) {
        try {
            // Adaptação: O 'textoPrincipal' do Conteudo é usado para o 'content' do tweet.
            String postId = twitterAPI.tweet(this.token, conteudo.getTextoPrincipal());
            Publicacao pub = new Publicacao(postId, "Twitter", "https://twitter.com/post/" + postId);
            return RespostaUnificada.sucesso(pub);
        } catch (IllegalArgumentException e) {
            // Tratamento de erro granular
            return RespostaUnificada.falha("Erro de autenticação no Twitter: " + e.getMessage());
        } catch (Exception e) {
            return RespostaUnificada.falha("Erro desconhecido ao postar no Twitter: " + e.getMessage());
        }
    }

    @Override
    public synchronized RespostaUnificada<Integer> obterEstatisticas(String idPost) {
        try {
            int stats = twitterAPI.getTweetStats(this.token, idPost);
            return RespostaUnificada.sucesso(stats);
        } catch (Exception e) {
            return RespostaUnificada.falha("Erro ao buscar estatísticas do Twitter: " + e.getMessage());
        }
    }
}