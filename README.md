# Projeto de estudo, seguindo o conteúdo apresentado no curso 'Adicionando Segurança a uma API REST com Spring Security'

O repositório contém, além das anotações nesse documento, um projeto Spring Boot desenvolvido seguindo o conteúdo
apresentado na aula.
Caso haja divergência deixo claro, em comentários no código, o porquê de ter divergido da implementação demonstrada no
curso.

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

- Por padrão, no spring security, o usuário "user" é utilizado em conjunto com uma senha, gerada a cada inicialização da
  aplicação, fornecida no terminal.
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
- Autorização baseada no path é um pouco mais complexa; Requer a definição de vários `SecurityFilterChain` na classe de
  configuração
  para definir a que URLs da aplicação cada *role* terá ou não acesso. Os links para a documentação listados nas
  referências,
  assim como a implementação na classe `security/WebSecurityConfiguration.java` explicam melhor.

### Usuários em Banco de Dados

- Trabalhando com usuários em banco de dadaos, a classe de usuário deve ser definida.
- Cuidado ao nomear essa classe como 'User', dessa forma o nome da tabela deve ser definido com
  `@Table(name = "tb_name")` pois a table 'users' é comummente utilizada.
- Na implementação, para que conseguisse construir a mesma funcionalidade desenvolvida no curso, precisei seguir um
  caminho
  um pouco diferente, utilizando um `AuthenticationProvider` na classe `WebSecurityConfig`;
  Segui um tutorial disponível no youtube ([neste link](https://www.youtube.com/watch?v=bOX1VYNqKCY)).

### JWT

- Token gerado a partir de meta-dados e dados de autenticação de usuário, codificado com algum algorítmo para encriptar
  os dados.
- Composto de três partes:
    - Header: Metadados sobre algorítmo usado para codificar os dados e o tipo de token gerado.
    - Payload: Dados de autenticação e autorização do usuário.
    - Signature: Assinatura (senha, secret key) para verificação da autenticidade da token.

### JWT no Spring Security

- Implementada utilizando jjwt.
- A implementação involve a criação de vários mecanismos para criar e autenticar a JWT;
  Para atingir uma implementação funcional, foi necessário um bom tanto de pesquisa, além das aulas,
  em sites como StackOverflow. A implementação final diverge, em alguns pontos, da implementação apresentada
  pois, diferente do curso, decidi por implementar a autenticação com JWT no mesmo projeto que a autenticação
  básica.

## Referências

- [Documentação Spring Security - Hello Spring Security](https://docs.spring.io/spring-security/reference/servlet/getting-started.html)
- [Documentação Spring Security - Basic Authentication](https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/basic.html)
- [Documentação Spring Security - Method Security](https://docs.spring.io/spring-security/reference/servlet/authorization/method-security.html)
- [Documentação Spring Security - Java Configuration](https://docs.spring.io/spring-security/reference/servlet/configuration/java.html)
- [Telusko (Youtube) - #33 Spring Security | Verify User from Database](https://www.youtube.com/watch?v=bOX1VYNqKCY)