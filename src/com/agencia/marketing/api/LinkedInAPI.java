package com.agencia.marketing.api;

/**
 * API "Incompatível" 2: LinkedIn.
 * Possui métodos com nomes e parâmetros diferentes, como 'sharePost'.
 */
public class LinkedInAPI {
    public boolean sharePost(String apiKey, String title, String textContent) {
        if (apiKey == null || !apiKey.startsWith("li-")) {
            System.err.println("LINKEDIN: Falha na autenticação. API Key inválida.");
            return false;
        }
        System.out.println("LINKEDIN: Compartilhando post: [" + title + "] " + textContent);
        return true; // Sucesso
    }
}