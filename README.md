# Projeto de estudo, seguindo o conteúdo apresentado no curso 'Adicionando Segurança a uma API REST com Spring Security'
O repositório contém, além das anotações nesse documento, um projeto Spring Boot desenvolvido seguindo o conteúdo apresentado na aula.
Caso haja divergência deixo claro, em comentários no código, o porquê de ter divergido da implementação demonstrada no curso.

## Setup
### Estrutura do projeto + dependências iniciais
O projeto foi estruturado da com ajuda da ferramenta [Spring Initializr](start.spring.io);
A seguinte configuração foi utilizada, incluindo dependências que, acredito, serão relevantes no curso.

![Imagem do setup configurado no Spring Initializr](./img/setup_initializr.png)

## Padões, Conceitos Aplicados e Definições
### Spring Security
- Biblioteca do ecossistema Spring responsável por fornecer recursos de autenticação e autorização.
- Adicionar a biblioteca ao projeto já inclúi configurações iniciais que tornam a aplicação mais segura.
#### Usuários
##### Em um contexto de testes simples
- Por padrão, no spring security, o usuário "user" é utilizado em conjunto com uma senha, gerada a cada inicialização da aplicação, fornecida no terminal.
- Pode-se criar um usuário padrão através do arquivo application.properties adicionando:
    ```
    spring.security.user.name = <nome-de-usuario>
    spring.security.user.password = <senha>
    spring.security.user.roles = <roles>
    ```
##### Em um contexto um pouco mais complexo
- Não se faz mais uso de usuários padrão; Usa-se usuário in-memory.
- Divergindo um pouco das aulas pois a mesma faz uso de classes depreciadas e removidas,
  precisei acessar a documentação do projeto Spring Security para encontrar
  maneiras mais atualizadas para se resolver os mesmos problemas.
- Usuários in-memory podem ser definidos utilizando a classe `InMemoryUserDetailsManager`.
- Autorização de métodos individuas permanece da forma que foi apresentada no curso: utilizando `@PreAuthorize`.

## Referências
- [Documentação Spring Security - Hello Spring Security](https://docs.spring.io/spring-security/reference/servlet/getting-started.html)
- [Documentação Spring Security - Method Security](https://docs.spring.io/spring-security/reference/servlet/authorization/method-security.html)
