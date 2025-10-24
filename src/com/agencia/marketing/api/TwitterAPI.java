package com.agencia.marketing.api;

import java.util.UUID;

/**
 * API "Incompatível" 1: Twitter.
 * Possui métodos específicos como 'tweet'.
 */
public class TwitterAPI {
    public String tweet(String token, String content) {
        if (token == null || !token.startsWith("tw-")) {
            throw new IllegalArgumentException("Token do Twitter inválido");
        }
        System.out.println("TWITTER: Postando tweet: '" + content + "'");
        return "twitter-post-id-" + UUID.randomUUID().toString();
    }

    public int getTweetStats(String token, String postId) {
        System.out.println("TWITTER: Obtendo estatísticas para o post: " + postId);
        return 150; // Retorna 150 curtidas
    }
}