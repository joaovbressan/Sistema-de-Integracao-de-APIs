# Projeto: Integração de APIs de Mídia Social com Padrão Adapter

[cite_start]Este projeto é uma implementação do exercício prático "Sistema de Integração de APIs de Mídia Social", utilizando o Padrão de Projeto Adapter[cite: 5]. O objetivo é criar um sistema unificado que gerencia publicações em múltiplas redes sociais (Twitter, LinkedIn, Instagram), cada uma com sua própria API incompatível.

## Padrão de Projeto: Adapter

[cite_start]O padrão **Adapter** foi utilizado para permitir que as interfaces incompatíveis das APIs de cada rede social trabalhem juntas sob uma interface unificada[cite: 6].

* **Interface Alvo (Target):** `GerenciadorMidiaSocial`. Esta é a interface que o nosso sistema cliente (`AgenciaDeMarketing`) espera usar.
* **APIs Incompatíveis (Adaptees):** `TwitterAPI`, `LinkedInAPI`, `InstagramAPI`. Estas são as classes "legadas" ou de terceiros que não podemos modificar.
* **Adapters:** `TwitterAdapter`, `LinkedInAdapter`, `InstagramAdapter`. Estas classes "traduzem" as chamadas da interface `GerenciadorMidiaSocial` para as chamadas específicas de cada API.

O projeto também utiliza o padrão **Factory Method** (através da `SocialMediaFactory`) para criar dinamicamente o adapter correto com base na configuração.

## Estrutura do Projeto

O código-fonte está organizado em pacotes dentro de `src/com/agencia/marketing/`:

* `api/`: Contém as classes que simulam as APIs externas e incompatíveis (`TwitterAPI`, `LinkedInAPI`, `InstagramAPI`).
* `core/`: Contém a interface alvo (`GerenciadorMidiaSocial`) e os modelos de dados unificados (`Conteudo`, `Publicacao`, `RespostaUnificada`).
* `adapter/`: Contém as classes Adapter, que implementam a interface `GerenciadorMidiaSocial` e "traduzem" as chamadas para as `api`s.
* `factory/`: Contém a `SocialMediaFactory`, responsável por criar e configurar os adapters.
* `AgenciaDeMarketing.java`: A classe cliente (ponto de entrada `main`) que consome a `SocialMediaFactory` e usa os adapters através da interface unificada.

## Como Compilar e Executar

Assumindo que todos os arquivos `.java` estão na estrutura de pastas `src/` correta:

1.  **Compilar o projeto:**
    Navegue até a pasta raiz (`ProjetoAgencia/`) e execute:

    ```bash
    # Para Windows (com backslash)
    javac -d build -sourcepath src src\com\agencia\marketing\AgenciaDeMarketing.java

    # Para Linux/macOS (com forward slash)
    javac -d build -sourcepath src src/com/agencia/marketing/AgenciaDeMarketing.java
    ```
    Isso compilará todos os arquivos necessários e colocará os `.class` na pasta `build/`.

2.  **Executar o projeto:**
    A partir da pasta raiz (`ProjetoAgencia/`), execute:

    ```bash
    # Para Windows (com ponto e vírgula no classpath)
    java -cp "build;." com.agencia.marketing.AgenciaDeMarketing

    # Para Linux/macOS (com dois pontos no classpath)
    java -cp "build:." com.agencia.marketing.AgenciaDeMarketing
    ```

3.  **Saída Esperada:**
    Você verá no console o log das postagens em cada plataforma, seguido por um teste de falha de autenticação.

## Diagrama de Classes

O diagrama abaixo ilustra a aplicação do Padrão Adapter. A `AgenciaDeMarketing` (Cliente) depende apenas da abstração `GerenciadorMidiaSocial` (Interface Alvo). A `SocialMediaFactory` instancia os `Adapter`s concretos, que implementam a interface alvo e, ao mesmo tempo, utilizam (via composição) as APIs `Adaptee` incompatíveis.

```mermaid
classDiagram
    direction LR

    class AgenciaDeMarketing {
        <<Cliente>>
        +main(String[] args)
        +processarPostagem(GerenciadorMidiaSocial, Conteudo)
    }

    class SocialMediaFactory {
        <<Factory>>
        -twitterAPI : TwitterAPI
        -linkedInAPI : LinkedInAPI
        -instagramAPI : InstagramAPI
        +createGerenciador(String, Map) : GerenciadorMidiaSocial
    }

    interface GerenciadorMidiaSocial {
        <<Interface>>
        +postarConteudo(Conteudo) : RespostaUnificada
        +obterEstatisticas(String) : RespostaUnificada
    }

    class TwitterAdapter {
        -twitterAPI : TwitterAPI
        +postarConteudo(Conteudo) : RespostaUnificada
        +obterEstatisticas(String) : RespostaUnificada
    }

    class LinkedInAdapter {
        -linkedInAPI : LinkedInAPI
        +postarConteudo(Conteudo) : RespostaUnificada
        +obterEstatisticas(String) : RespostaUnificada
    }

    class InstagramAdapter {
        -instagramAPI : InstagramAPI
        +postarConteudo(Conteudo) : RespostaUnificada
        +obterEstatisticas(String) : RespostaUnificada
    }

    class TwitterAPI {
        <<Adaptee>>
        +tweet(String, String) : String
        +getTweetStats(String, String) : int
    }
    
    class LinkedInAPI {
        <<Adaptee>>
        +sharePost(String, String, String) : boolean
    }

    class InstagramAPI {
        <<Adaptee>>
        +postMedia(String, byte[], String) : String
    }

    class Conteudo {
        -textoPrincipal : String
    }
    class RespostaUnificada {
        <<Generic>>
        -dados : T
        -erro : String
    }

    ' Relacionamentos
    AgenciaDeMarketing ..> SocialMediaFactory : "usa"
    AgenciaDeMarketing ..> GerenciadorMidiaSocial : "depende de"
    AgenciaDeMarketing ..> Conteudo : "cria"

    SocialMediaFactory ..> GerenciadorMidiaSocial : "cria"
    SocialMediaFactory ..> TwitterAdapter : "cria"
    SocialMediaFactory ..> LinkedInAdapter : "cria"
    SocialMediaFactory ..> InstagramAdapter : "cria"

    GerenciadorMidiaSocial <|.. TwitterAdapter : "implementa"
    GerenciadorMidiaSocial <|.. LinkedInAdapter : "implementa"
    GerenciadorMidiaSocial <|.. InstagramAdapter : "implementa"

    TwitterAdapter o--> TwitterAPI : "usa (composição)"
    LinkedInAdapter o--> LinkedInAPI : "usa (composição)"
    InstagramAdapter o--> InstagramAPI : "usa (composição)"
    
    GerenciadorMidiaSocial ..> RespostaUnificada : "retorna"