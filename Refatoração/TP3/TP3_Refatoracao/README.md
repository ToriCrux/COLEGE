# Refatoração de Sistema de Faturas – E-commerce

Este projeto é uma refatoração completa de um sistema inicial de geração de faturas e envio de e-mails para uma startup de e-commerce. A aplicação foi originalmente desenvolvida de forma apressada, apresentando problemas estruturais como alto acoplamento, ausência de encapsulamento e lógica espalhada.

A refatoração seguiu princípios de **design orientado a objetos**, com foco em legibilidade, manutenção e responsabilidade única.

---

## 🔧 Tecnologias

- Java 21
- IntelliJ IDEA (ou qualquer IDE compatível)
- Execução via linha de comando ou ambiente gráfico

---

## Melhorias aplicadas

### ✅ 1. Encapsulamento e coesão
- Substituição de atributos públicos por privados com acesso controlado.
- Criação da classe `OrderItem` para substituir listas paralelas.

### ✅ 2. Redução de acoplamento
- A `Order` agora encapsula a chamada ao `EmailService`, usando um método de domínio (`confirmOrder()`).

### ✅ 3. Criação de entidades do domínio
- `Client`: encapsula nome e e-mail, com validações.
- `Email`: representa o e-mail completo com remetente, assunto e corpo.

### ✅ 4. Separação de responsabilidades
- `InvoicePrinter`: responsável apenas por imprimir a fatura.
- `EmailService`: responsável pelo envio (simulado) dos e-mails.
- `Order`: apenas realiza cálculos e orquestra ações.

### ✅ 5. Funções auxiliares com nomes claros
- Extração de métodos privados como `buildEmailConfirmation()`, `getSubtotal()` e `printClientInfo()`.

### ✅ 6. Validações e consistência
- Proteções contra pedidos vazios, itens inválidos e clientes incompletos.

---


