package com.agencia.marketing.adapter;

import com.agencia.marketing.api.LinkedInAPI;
import com.agencia.marketing.core.Conteudo;
import com.agencia.marketing.core.GerenciadorMidiaSocial;
import com.agencia.marketing.core.Publicacao;
import com.agencia.marketing.core.RespostaUnificada;
import java.util.UUID;

/**
 * TAREFA 1: Adapter para a LinkedInAPI.
 * Traduz 'postarConteudo' para 'sharePost'.
 */
public class LinkedInAdapter implements GerenciadorMidiaSocial {
    private final LinkedInAPI linkedInAPI; // Composição
    private final String apiKey;

    public LinkedInAdapter(LinkedInAPI linkedInAPI, String apiKey) {
        this.linkedInAPI = linkedInAPI;
        this.apiKey = apiKey;
    }

    @Override
    public synchronized RespostaUnificada<Publicacao> postarConteudo(Conteudo conteudo) {
        try {
            // Adaptação: Usa 'titulo' e 'textoPrincipal' para 'title' e 'textContent'.
            boolean sucesso = linkedInAPI.sharePost(this.apiKey, conteudo.getTitulo(), conteudo.getTextoPrincipal());
            if (sucesso) {
                String id = "linkedin-post-id-" + UUID.randomUUID().toString(); // LinkedIn API (mock) não retorna ID
                Publicacao pub = new Publicacao(id, "LinkedIn", "https://linkedin.com/feed/update/" + id);
                return RespostaUnificada.sucesso(pub);
            } else {
                return RespostaUnificada.falha("Falha ao postar no LinkedIn (API retornou false).");
            }
        } catch (Exception e) {
            return RespostaUnificada.falha("Erro desconhecido ao postar no LinkedIn: " + e.getMessage());
        }
    }

    @Override
    public synchronized RespostaUnificada<Integer> obterEstatisticas(String idPost) {
        // A API mock não tem estatísticas, então retornamos falha.
        return RespostaUnificada.falha("Função de estatísticas não suportada pelo LinkedInAdapter.");
    }
}