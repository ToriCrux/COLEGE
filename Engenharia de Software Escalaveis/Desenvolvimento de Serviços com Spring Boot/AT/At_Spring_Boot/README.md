# API Acadêmica

Este projeto é uma API construída como parte de um assessment da faculdade, utilizando **Spring Boot**.  
O objetivo é gerenciar **alunos, professores, disciplinas e matrículas**, simulando um ambiente acadêmico real.

Atualmente, a aplicação está disponível em produção através de uma **VPS na Hostinger**, utilizando **Docker** para orquestração e o banco de dados **MongoDB** para armazenamento das informações.

---

## Funcionalidades

- Cadastro e listagem de **professores** (com autenticação via JWT)
- Cadastro e listagem de **alunos**
- Cadastro e listagem de **disciplinas**
- Cadastro e listagem de **matrículas**
- Atualização de **notas**
- Consultas específicas de **aprovados** e **reprovados**

---

## Banco de Dados

- Banco utilizado em produção: **MongoDB**
- Gerenciado via container **Docker**
- A configuração local pode ser adaptada no `application.properties`

---

## Endpoints da API

A API está rodando em:  
`https://victoria-cruz-at-springboot-academico-api.ksexdv.easypanel.host`

### Autenticação
- **POST** `/api/auth/register` → Registra professor
```json
{
  "email": "professor@example.com",
  "senha": "123456"
}
```

- **POST** `/api/auth/login` → Login de professor (retorna JWT)
```json
{
  "email": "professor@example.com",
  "senha": "123456"
}
```

---

### Alunos

- **POST** `/api/alunos` → Cadastra Aluno
- **GET** `/api/alunos` → Lista Aluno
```json
{
  "nome": "Maria Silva",
  "cpf": "12345678900",
  "email": "maria@example.com",
  "telefone": "11999999999",
  "endereco": {
    "rua": "Rua Teste",
    "numero": "100",
    "bairro": "Centro",
    "cidade": "São Paulo",
    "estado": "SP",
    "cep": "12345000"
  }
}
```

---

### Disciplinas

- **POST** `/api/disciplinas` → Cadastra Disciplinas
- **GET** `/api/disciplinas` → Lista Disciplinas
```json
{
    "nome": "Design de Software",
    "codigo": "DS101",
    "professor_id": "68d31a95fdcba259fd6c582a"
}
```

---

### Matriculas

- **POST** `/api/matriculas` → Matricula aluno em disciplina

```json
{
  "alunoId": "68d31cbbfdcba259fd6c582c",
  "disciplinaId": "68d31e3bfdcba259fd6c582f"
}
```


- **GET** `/api/matriculas` → Lista todas as matrículas
- **PATCH** `/api/matriculas/{id}/nota` → Atribui nota
```json
{
  "nota": 8.5
}
```

---

### Consultas Específicas

- GET /api/matriculas/aprovados → Lista todos os alunos aprovados
- GET /api/matriculas/reprovados → Lista todos os alunos reprovados
- GET /api/matriculas/disciplina/{disciplinaId}/aprovados → Lista aprovados de uma disciplina
- GET /api/matriculas/disciplina/{disciplinaId}/reprovados → Lista reprovados de uma disciplina
- GET /api/matriculas/aluno/{alunoId} → Lista notas de um aluno específico

---

### Testes Automatizados

O projeto conta com testes unitários e de integração, com cobertura de código medida pelo JaCoCo.

Para rodar localmente:
```bash
mvn test
mvn jacoco:report
```

Relatório disponível em: btarget/site/jacoco/index.html

---

### Deploy

- Empacotamento do projeto com Maven (mvn clean package -DskipTests)
- Arquivo .jar incluído em uma imagem Docker via Dockerfile
- Container executado em VPS Hostinger, junto com MongoDB
- API publicada e disponível para consumo externo
