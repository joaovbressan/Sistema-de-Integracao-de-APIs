package com.agencia.marketing.factory;

import com.agencia.marketing.adapter.InstagramAdapter;
import com.agencia.marketing.adapter.LinkedInAdapter;
import com.agencia.marketing.adapter.TwitterAdapter;
import com.agencia.marketing.api.InstagramAPI;
import com.agencia.marketing.api.LinkedInAPI;
import com.agencia.marketing.api.TwitterAPI;
import com.agencia.marketing.core.GerenciadorMidiaSocial;
import java.util.Map;
import java.util.Objects;

public class SocialMediaFactory {
   
    private final TwitterAPI twitterAPI;
    private final LinkedInAPI linkedInAPI;
    private final InstagramAPI instagramAPI;

    public SocialMediaFactory() {
     
        this.twitterAPI = new TwitterAPI();
        this.linkedInAPI = new LinkedInAPI();
        this.instagramAPI = new InstagramAPI();
    }

    /**
     * Cria o Adapter (Gerenciador) apropriado com base na plataforma e configuração.
     * @param plataforma O nome da plataforma (ex: "twitter", "linkedin").
     * @param config Um mapa com chaves de autenticação (ex: "token", "apiKey").
     * @return Uma instância de GerenciadorMidiaSocial (o Adapter).
     * @throws IllegalArgumentException Se a plataforma for desconhecida ou a config estiver inválida.
     */
    public GerenciadorMidiaSocial createGerenciador(String plataforma, Map<String, String> config) {
        Objects.requireNonNull(plataforma, "Plataforma não pode ser nula");
        Objects.requireNonNull(config, "Configuração não pode ser nula");

        switch (plataforma.toLowerCase()) {
            case "twitter":
                String token = config.get("token");
                if (token == null) throw new IllegalArgumentException("Configuração 'token' é obrigatória para Twitter.");
            
                return new TwitterAdapter(this.twitterAPI, token);

            case "linkedin":
                String apiKey = config.get("apiKey");
                if (apiKey == null) throw new IllegalArgumentException("Configuração 'apiKey' é obrigatória para LinkedIn.");
                return new LinkedInAdapter(this.linkedInAPI, apiKey);

            case "instagram":
                String authToken = config.get("authToken");
                if (authToken == null) throw new IllegalArgumentException("Configuração 'authToken' é obrigatória para Instagram.");
                return new InstagramAdapter(this.instagramAPI, authToken);

            default:
                throw new IllegalArgumentException("Plataforma desconhecida: " + plataforma);
        }
    }
}