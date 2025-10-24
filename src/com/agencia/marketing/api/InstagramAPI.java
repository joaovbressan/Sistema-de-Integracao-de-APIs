package com.agencia.marketing.api;

import java.util.UUID;

/**
 * API "Incompatível" 3: Instagram.
 * Foca em mídia (fotos/vídeos) e tem outra estrutura.
 */
public class InstagramAPI {
    public String postMedia(String authToken, byte[] mediaData, String caption) {
        if (authToken == null || !authToken.startsWith("ig-")) {
            return null; // Falha na autenticação
        }
        System.out.println("INSTAGRAM: Postando mídia com " + mediaData.length + " bytes. Legenda: '" + caption + "'");
        return "instagram-media-id-" + UUID.randomUUID().toString();
    }
}