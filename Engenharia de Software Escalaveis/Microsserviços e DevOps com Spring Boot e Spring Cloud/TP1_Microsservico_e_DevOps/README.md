# Projeto: Microsserviços com Spring Cloud

## Objetivo
Este projeto demonstra a criação de uma arquitetura de **microsserviços** utilizando o **Spring Cloud**, com **descoberta de serviços (Eureka)** e **comunicação via OpenFeign** entre dois módulos principais:
- `product-service`
- `order-service`

---
## Estrutura do Projeto

| Serviço | Função | Porta | Comunicação |
|----------|--------|--------|--------------|
| **discovery-server** | Registro e descoberta de serviços (Eureka Server) | 8761 | Centraliza os microsserviços |
| **product-service** | Cadastro e listagem de produtos | 8081 | Registra-se no Eureka |
| **order-service** | Consulta produtos e simula pedidos | 8082 | Consome o `product-service` via Feign |

---

## Tecnologias Utilizadas
- Java 17
- Spring Boot 3
- Spring Cloud Netflix Eureka
- Spring Cloud OpenFeign
- Spring Web
- Maven

---

## Execução

1. Inicie o **Eureka Server** (`discovery-server`): http://localhost:8761/
2. Execute o **Product Service** (porta `8081`).
3. Execute o **Order Service** (porta `8082`).

Verifique no painel do Eureka se ambos estão com status **UP**.

---

## Testes de API

### Criar Produto
```bash

POST http://localhost:8081/products

{
"name": "Ladrilho Azul",
"price": 45.90
}

Lembre-se de verificar se a opção Content-Type: application/json, está habilitada no Headers.
```

### Criar Produto
```bash

GET http://localhost:8082/orders
```