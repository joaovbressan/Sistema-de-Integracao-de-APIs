package com.agencia.marketing;

import com.agencia.marketing.core.Conteudo;
import com.agencia.marketing.core.GerenciadorMidiaSocial;
import com.agencia.marketing.core.Publicacao;
import com.agencia.marketing.core.RespostaUnificada;
import com.agencia.marketing.factory.SocialMediaFactory;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AgenciaDeMarketing {
    public static void main(String[] args) {
        
        SocialMediaFactory factory = new SocialMediaFactory();

        Map<String, String> configTwitter = new ConcurrentHashMap<>();
        configTwitter.put("token", "tw-meu-token-secreto");

        Map<String, String> configLinkedIn = new ConcurrentHashMap<>();
        configLinkedIn.put("apiKey", "li-minha-api-key");
        
        Map<String, String> configInstagram = new ConcurrentHashMap<>();
        configInstagram.put("authToken", "ig-meu-auth-token");

        GerenciadorMidiaSocial gerenciadorTwitter = factory.createGerenciador("twitter", configTwitter);
        GerenciadorMidiaSocial gerenciadorLinkedIn = factory.createGerenciador("linkedin", configLinkedIn);
        GerenciadorMidiaSocial gerenciadorInstagram = factory.createGerenciador("instagram", configInstagram);

        System.out.println("--- Iniciando postagens ---");

  
 
        Conteudo tweet = new Conteudo("Olá mundo, este é meu primeiro tweet via Adapter!");
        processarPostagem(gerenciadorTwitter, tweet);

        Conteudo postLinkedIn = new Conteudo("Novo Artigo", "Como o Padrão Adapter salvou nosso projeto de integração.");
        processarPostagem(gerenciadorLinkedIn, postLinkedIn);

        byte[] minhaFoto = new byte[]{0x1, 0x2, 0x3}; 
        Conteudo postInstagram = new Conteudo("Foto da equipe!", minhaFoto);
        processarPostagem(gerenciadorInstagram, postInstagram);
        
      
        System.out.println("\n--- Testando falha de autenticação ---");
        Map<String, String> configInvalida = new ConcurrentHashMap<>();
        configInvalida.put("token", "token-errado"); 
        GerenciadorMidiaSocial twitterInvalido = factory.createGerenciador("twitter", configInvalida);
        processarPostagem(twitterInvalido, tweet);
    }

    public static void processarPostagem(GerenciadorMidiaSocial gerenciador, Conteudo conteudo) {
        RespostaUnificada<Publicacao> resposta = gerenciador.postarConteudo(conteudo);

        if (resposta.isSucesso()) {
            System.out.println("SUCESSO: Postagem realizada.");
            System.out.println("Detalhes: " + resposta.getDados());
        } else {
            System.err.println("FALHA: Não foi possível postar.");
            System.err.println("Erro: " + resposta.getErro());
        }
        System.out.println("--------------------");
    }
}