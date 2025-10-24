package com.agencia.marketing.adapter;

import com.agencia.marketing.api.InstagramAPI;
import com.agencia.marketing.core.Conteudo;
import com.agencia.marketing.core.GerenciadorMidiaSocial;
import com.agencia.marketing.core.Publicacao;
import com.agencia.marketing.core.RespostaUnificada;

/**
 * TAREFA 1: Adapter para a InstagramAPI.
 * Traduz 'postarConteudo' para 'postMedia'.
 */
public class InstagramAdapter implements GerenciadorMidiaSocial {
    private final InstagramAPI instagramAPI; // Composição
    private final String authToken;

    public InstagramAdapter(InstagramAPI instagramAPI, String authToken) {
        this.instagramAPI = instagramAPI;
        this.authToken = authToken;
    }

    @Override
    public synchronized RespostaUnificada<Publicacao> postarConteudo(Conteudo conteudo) {
        try {
            if (conteudo.getMidia() == null) {
                return RespostaUnificada.falha("Erro no Instagram: Mídia é obrigatória.");
            }
            // Adaptação: Usa 'midia' e 'textoPrincipal' (como legenda).
            String mediaId = instagramAPI.postMedia(this.authToken, conteudo.getMidia(), conteudo.getTextoPrincipal());
            
            if (mediaId != null) {
                Publicacao pub = new Publicacao(mediaId, "Instagram", "https://instagram.com/p/" + mediaId);
                return RespostaUnificada.sucesso(pub);
            } else {
                return RespostaUnificada.falha("Falha na autenticação com o Instagram.");
            }
        } catch (Exception e) {
            return RespostaUnificada.falha("Erro desconhecido ao postar no Instagram: " + e.getMessage());
        }
    }

    @Override
    public synchronized RespostaUnificada<Integer> obterEstatisticas(String idPost) {
        return RespostaUnificada.falha("Função de estatísticas não suportada pelo InstagramAdapter.");
    }
}