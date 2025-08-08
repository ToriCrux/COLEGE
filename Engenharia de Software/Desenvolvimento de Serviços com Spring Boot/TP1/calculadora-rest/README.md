# Calculadora REST API

Este projeto foi desenvolvido como parte de uma atividade da disciplina de Desenvolvimento de Serviços com Spring Boot.

A aplicação consiste em uma API REST simples capaz de realizar operações matemáticas básicas: adição, subtração, multiplicação, divisão e exponenciação. Cada operação pode ser acessada via métodos GET ou POST.

## Tecnologias utilizadas

- Java 17
- Spring Boot 3.5.4
- Maven
- Spring Web
- Spring Boot CLI (para geração e execução do projeto)

## Como executar o projeto

1. Clone o repositório ou baixe os arquivos do projeto.
2. No terminal, acesse a pasta raiz do projeto.

Se estiver usando **Linux/WSL**:

```bash
./mvnw spring-boot:run
```

Se estiver usando Windows com Maven instalado:

```bash
./mvnw spring-boot:run
```

### Endpoints disponíveis
Base URL: http://localhost:8080/api

- /somar?a=2&b=3
- /subtrair?a=5&b=2
- /multiplicar?a=4&b=3
- /dividir?a=10&b=2
- /exponenciar?a=2&b=3

Todos os endpoints aceitam requisições GET e POST com os parâmetros a e b. Explore a vontade!