# Desafio Itaú - API de Transações

API REST em Java e Spring Boot que recebe transações em memória e calcula estatísticas das transações dos últimos 60 segundos.

Este projeto foi desenvolvido com base no desafio oficial: [feltex/desafio-itau-backend](https://github.com/feltex/desafio-itau-backend/tree/main).

## Pré-requisitos

- **JDK 21** (versão definida no `pom.xml`)
- **Maven** (opcional — o projeto inclui Maven Wrapper: `mvnw` / `mvnw.cmd`)
- **Postman** (Desktop ou Web) para testes manuais da API

## Instalação

Clone o repositório e entre na pasta do projeto:

```powershell
git clone https://github.com/Allsmind/desafio-itau-backend.git
cd desafio-itau-backend
```

No Linux ou macOS:

```bash
git clone https://github.com/Allsmind/desafio-itau-backend.git
cd desafio-itau-backend
```

## Executando a aplicação

Com a API em execução, ela ficará disponível em `http://localhost:8080`.

**Windows (PowerShell ou CMD):**

```powershell
.\mvnw.cmd spring-boot:run
```

**Linux / macOS:**

```bash
./mvnw spring-boot:run
```

Aguarde a mensagem `Started DesafioItauBackendApplication` no console. Para encerrar a aplicação, use `Ctrl+C`.

## Testes automatizados

Para rodar os testes com Maven:

```powershell
.\mvnw.cmd test
```

```bash
./mvnw test
```

Atualmente o projeto possui o teste de contexto em `DesafioItauBackendApplicationTests`.

## Endpoints

| Método | Rota           | Sucesso     | Corpo de requisição / resposta                          |
|--------|----------------|-------------|---------------------------------------------------------|
| POST   | `/transacao`   | 201 Created | `{ "valor": number, "dataHora": "ISO-8601" }`           |
| DELETE | `/transacao`   | 200 OK      | Sem corpo                                               |
| GET    | `/estatistica` | 200 OK      | `{ "count", "sum", "avg", "min", "max" }`               |

### Códigos de resposta adicionais

| Código | Situação                                                                 |
|--------|--------------------------------------------------------------------------|
| 400    | JSON inválido ou malformado                                              |
| 422    | Transação rejeitada (valor inválido, data futura, campos ausentes, etc.) |

### Exemplo de transação (POST `/transacao`)

```json
{
  "valor": 123.45,
  "dataHora": "2026-05-26T14:30:00.000-03:00"
}
```

Header obrigatório: `Content-Type: application/json`.

## Documentação Swagger (OpenAPI)

A API é documentada com [SpringDoc OpenAPI](https://springdoc.org/). Com a aplicação em execução, acesse:

| Recurso        | URL                                      |
|----------------|------------------------------------------|
| Swagger UI     | http://localhost:8080/swagger-ui.html    |
| Especificação  | http://localhost:8080/api-docs           |

### Como usar o Swagger UI

1. Execute a aplicação (`.\mvnw.cmd spring-boot:run`).
2. Abra http://localhost:8080/swagger-ui.html no navegador.
3. Expanda o grupo **Transação** ou **Estatística**.
4. Clique em **Try it out**, preencha o body (quando aplicável) e em **Execute** para enviar a requisição.

No `POST /transacao`, use um JSON como:

```json
{
  "valor": 123.45,
  "dataHora": "2026-05-26T14:30:00.000-03:00"
}
```

A documentação descreve os códigos de resposta **201**, **200**, **400** e **422** conforme o enunciado do desafio.

## Testando com Postman

### 1. Subir a API

Execute a aplicação antes de enviar qualquer requisição (porta **8080**).

### 2. Importar a coleção

1. Abra o [Postman](https://www.postman.com/downloads/).
2. Clique em **Import**.
3. Selecione o arquivo [`postman/Transação.postman_collection.json`](postman/Transação.postman_collection.json) deste repositório.

Todas as requisições da coleção usam a URL base `http://localhost:8080`.

### 3. Ordem sugerida de testes

| Ordem | Request na coleção | Método | Endpoint        | Resultado esperado |
|-------|--------------------|--------|-----------------|--------------------|
| 1     | Limpar Cache       | DELETE | `/transacao`    | 200 OK             |
| 2     | Transacao1         | POST   | `/transacao`    | 201 Created        |
| 3     | Transacao2         | POST   | `/transacao`    | 201 Created        |
| 4     | Estatistica        | GET    | `/estatistica`  | 200 OK com JSON    |

**Limpar Cache** — remove todas as transações armazenadas em memória.

**Transacao1** — envia uma transação com data fixa no passado (`2024-04-14`). A transação é aceita (201), mas não entra na janela dos últimos 60 segundos ao consultar estatísticas.

**Transacao2** — envia uma transação com `dataHora` usando `{{$timestamp}}` (data/hora atual do Postman). Ideal para validar o endpoint de estatísticas.

**Estatistica** — retorna `count`, `sum`, `avg`, `min` e `max` das transações dos últimos 60 segundos. Se não houver transações nesse intervalo, todos os valores serão `0`.

### 4. Dica para validar estatísticas

Para ver valores diferentes de zero em `/estatistica`:

1. Execute **Limpar Cache**.
2. Envie uma ou mais requisições **Transacao2** (ou crie um POST manual com `dataHora` recente).
3. Em seguida, execute **Estatistica**.

### 5. Criar requisição manual no Postman

- Método: `POST`
- URL: `http://localhost:8080/transacao`
- Headers: `Content-Type: application/json`
- Body (raw JSON):

```json
{
  "valor": 123.45,
  "dataHora": "2026-05-26T14:30:00.000-03:00"
}
```

Use uma data/hora atual ou no passado (nunca no futuro).

## Estrutura do projeto

```
src/main/java/com/somer/renato/desafioitaubackend/
├── config/         # Configuração OpenAPI / Swagger
├── transacao/      # POST e DELETE /transacao
├── estatistica/    # GET /estatistica
└── exception/      # Tratamento global de erros
postman/            # Coleção Postman para testes manuais
```
