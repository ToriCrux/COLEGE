# TP2 — Desenvolvimento de Serviços com Spring Boot (CRUD de Produtos)

Este repositório contém uma API REST de **Produtos** construída com **Spring Boot 3.5.4** e **Java 21**, seguindo o enunciado do trabalho (TP2). A aplicação implementa um CRUD completo utilizando `@RequestMapping`, validação com **Jakarta Bean Validation**, persistência com **Spring Data JPA** e banco **H2**. O tratamento de erros é centralizado via **`@RestControllerAdvice`** para garantir códigos de status coerentes com REST.

---

## ✨ O que a API faz
- Exponde endpoints REST para **criar, listar, buscar, atualizar e excluir** produtos.
- Recebe e responde em **JSON**.
- **Valida** entrada (nome obrigatório, preço não negativo) e retorna **400** quando inválido.
- Retorna **404** quando o recurso não existe.
- Persiste dados em **H2 em memória** (dev) — com opção de **persistir em arquivo**.

---

## 🧰 Stack e versões
- **Java**: 21 (LTS)  
- **Spring Boot**: 3.5.4  
- **Maven**: wrapper incluído (`mvnw`/`mvnw.cmd`)  
- **Dependências principais**:  
  - `spring-boot-starter-web` (REST + Tomcat + Jackson)  
  - `spring-boot-starter-data-jpa` (JPA/Hibernate)  
  - `com.h2database:h2` (banco em memória)  
  - `spring-boot-starter-validation` (Bean Validation)  
  - `spring-boot-starter-test` (JUnit/MockMvc)  
  - `lombok` (opcional, reduzir boilerplate)
