![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

[![logo spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/)
[![logo spring-boot](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot)](https://spring.io/projects/spring-boot)

![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![Liquibase](https://img.shields.io/badge/liquibase-%23F37626.svg?style=for-the-badge&logo=liquibase&logoColor=white)

![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)
![GNU Make](https://img.shields.io/badge/GNU%20Make-000000?style=for-the-badge&logo=GNU%20Make&logoColor=white)

![Swagger](https://img.shields.io/badge/Swagger-%2385EA2D.svg?style=for-the-badge&logo=Swagger&logoColor=black)
![Maven](https://img.shields.io/badge/maven-%23C71A36.svg?style=for-the-badge&logo=apache-maven&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![Git](https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white)

![GitHub language count](https://img.shields.io/github/languages/count/fsales/fiap-tech-chalenge-fase5)
![GitHub top language](https://img.shields.io/github/languages/top/fsales/fiap-tech-chalenge-fase5)
![GitHub repo size](https://img.shields.io/github/repo-size/fsales/fiap-tech-chalenge-fase5)
![GitHub](https://img.shields.io/github/license/fsales/fiap-tech-chalenge-fase5)
![GitHub last commit](https://img.shields.io/github/last-commit/fsales/fiap-tech-chalenge-fase5)
![GitHub contributors](https://img.shields.io/github/contributors/fsales/fiap-tech-chalenge-fase5)
![GitHub issues](https://img.shields.io/github/issues/fsales/fiap-tech-chalenge-fase5)
![GitHub pull requests](https://img.shields.io/github/issues-pr/fsales/fiap-tech-chalenge-fase5)

<p align="center">
 <img src="https://img.shields.io/static/v1?label=GitHub&message=@Wells-store&color=8257E5&labelColor=000000" alt="@wells-store" />
 <img src="https://img.shields.io/static/v1?label=Tipo&message=Tech%20Chalenge&color=8257E5&labelColor=000000" alt="Tech Chalenge" />
</p>


- [Projeto Wells Pagamento](#projeto-wells-pagamento)
  - [Tecnologias Utilizadas](#tecnologias-utilizadas)
  - [Clean Architecture](#clean-architecture)
    - [Ambiente de Desenvolvimento](#ambiente-de-desenvolvimento)
      - [Configuração Comuns](#configuração-comuns)
        - [Construir o Projeto Java](#construir-o-projeto-java)
    - [Banco de Dados](#banco-de-dados)
    - [Arquivos de Configuração](#arquivos-de-configuração)
      - [Configuração no Spring Boot \[^1\]](#configuração-no-spring-boot-1)
        - [1. Arquivo Principal (`application.properties` ou `application.yml`):](#1-arquivo-principal-applicationproperties-ou-applicationyml)
        - [2. Perfis de Configuração:](#2-perfis-de-configuração)
        - [3. Prioridade de Carregamento:](#3-prioridade-de-carregamento)
        - [4. Variáveis de Ambiente e Linha de Comando:](#4-variáveis-de-ambiente-e-linha-de-comando)
        - [5. Configuração Programática:](#5-configuração-programática)
      - [Migração no Liquibase \[^2\]](#migração-no-liquibase-2)
        - [Estrutura do Banco de Dados](#estrutura-do-banco-de-dados)
      - [Arquivo `.env`](#arquivo-env)
  - [Funcionalidades](#funcionalidades)
  - [Endpoints do Módulo](#endpoints-do-módulo)
    - [API de Pagamento](#api-de-pagamento)
      - [Cria um novo pagamento](#cria-um-novo-pagamento)
      - [Atualiza um pagamento existente](#atualiza-um-pagamento-existente)
      - [Confirma um pagamento](#confirma-um-pagamento)
      - [Cancela um pagamento](#cancela-um-pagamento)
      - [Consulta um pagamento por ID](#consulta-um-pagamento-por-id)
      - [Lista todos os pagamentos](#lista-todos-os-pagamentos)

# Projeto Wells Pagamento

Este projeto é um microserviço de pagamento que faz parte do sistema Wells Store. Ele é responsável por gerenciar todas as operações relacionadas ao pagamento, como criar, atualizar, confirmar e cancelar pagamentos.

## Tecnologias Utilizadas

- Java
- Spring Boot
- Spring Data JPA
- Spring Cloud
- PostgreSQL
- Liquibase
- RabbitMQ
- Consul

## Clean Architecture

- [Clean Architecture](../README.md#clean-architecture)

### Ambiente de Desenvolvimento

Pré-requisitos e [configurações do ambiente de desenvolvimento](../README.md#configuração-do-ambiente-de-desenvolvimento).

#### Configuração Comuns

Este projeto utiliza o Spring Boot, portanto, pode ser facilmente executado a partir da linha de comando com o Maven ou diretamente de uma IDE que suporte o Spring Boot.

- [Pré-requisitos](../README.md#pré-requisitos)
- [Realizar do clone do projeto](../README.md#realizar-do-clone-do-projeto)
- [GNU Make](../README.md#gnu-make)
  - [Construção e Execução](../README.md#construção-e-execução)

**Passos para Executar a Partir do diretório `rais`**

##### Construir o Projeto Java

Como este projeto utiliza o Maven e é um módulo do projeto Wells Store, a construção do projeto Java é feita a partir do diretório `rais`.

### Banco de Dados

Foi escolhido o banco de dados Postgres para armazenar os dados da aplicação. O banco de dados é executado em um contêiner Docker, que pode ser iniciado com o comando descrito em  [Docker - Postgres](../docker/README.md#docker-compose-do-postgres).


### Arquivos de Configuração

#### Configuração no Spring Boot [^1]

##### 1. Arquivo Principal (`application.properties` ou `application.yml`):

- Este arquivo, localizado em `src/main/resources`, serve como o principal repositório de configurações globais.

##### 2. Perfis de Configuração:

- Arquivos específicos para perfis, tais como `application-dev.properties` ou `application-prod.yml`, são destinados a configurações específicas de ambiente.

##### 3. Prioridade de Carregamento:

- A ordem de prioridade segue este padrão:
    1. Configurações específicas do perfil (por exemplo, `application-dev.properties`).
    2. Configurações específicas do perfil no formato YAML (por exemplo, `application-dev.yml`).
    3. Configurações no arquivo principal `application.properties`.
    4. Configurações no arquivo principal `application.yml`.
- Configurações de perfis e formato YAML têm precedência sobre configurações no formato de propriedades.

##### 4. Variáveis de Ambiente e Linha de Comando:

- As configurações podem ser substituídas por variáveis de ambiente ou argumentos da linha de comando.

##### 5. Configuração Programática:

- É possível realizar configurações programáticas usando classes Java específicas.

---

As configurações do módulo estão localizadas no diretório `src/main/resources`.

Os arquivos de configuração do projeto incluem:

- `application-dev.yml`: Configurações para o ambiente de desenvolvimento.
- `application-prod.yml`: Configurações para o ambiente de produção.
- `application.yml`: Configurações padrão.
- `application-actuator.yml`: Configurações específicas do Actuator.
- `application-springdoc.yml`: Configurações específicas do Springdoc.

#### Migração no Liquibase [^2]

O termo "migration" no Liquibase refere-se a alterações planejadas no esquema de um banco de dados. Essas alterações são gerenciadas de forma controlada e versionada pela ferramenta [Liquibase](https://docs.liquibase.com/), permitindo uma abordagem organizada.

Essas migrações são definidas em arquivos de changelog, encapsuladas em "changesets" e aplicadas de maneira sequencial e ordenada. Algumas características-chave incluem a DSL do Liquibase, permitindo descrições independentes do banco de dados, e suporte para rollback, multiambiente e integração com ferramentas de construção.

##### Estrutura do Banco de Dados

As configurações do banco de dados estão armazenadas no diretório `src/main/resources/db/changelog/changes/postgres`.

As definições de migração do banco de dados estão presentes nos seguintes arquivos:

- `V0__CREATE_SCHEMA.sql`: Responsável pela criação do esquema do banco de dados.
- `V2__CREATE_PAGAMENTO.sql`: Cria a tabela `pagamento`.
- `V3__DML.sql`: Insere dados na tabela `pagamento`.

#### Arquivo `.env`

O arquivo `.env` é utilizado para armazenar informações sensíveis, como chaves e senhas, separando esses dados do código-fonte. Isso facilita a configuração do software em diferentes ambientes, embora seja importante notar que o `.env` por si só não garante total segurança.

No projeto Wells Usuário, o arquivo `.env` está localizado no diretório `wells-store/wells-pagamento` e contém as variáveis de ambiente necessárias para a execução do módulo.

O arquivo está disponível no repositório do projeto através do link [`.env`](.env).

[Construção e Execução](/wells-store/README.md#construção-e-execução)

## Funcionalidades

- Cadastrar um novo pagamento
- Atualizar um pagamento existente
- Confirmar um pagamento
- Cancelar um pagamento
- Consultar um pagamento por ID
- Listar todos os pagamentos

## Endpoints do Módulo

Nesta seção, apresentamos a lista de endpoints disponíveis no módulo , todos devidamente documentados no Swagger. A documentação pode ser acessada por meio do link [Swagger](http://localhost:8125/swagger-ui.html).

Para realizar requisições HTTP, sugerimos a utilização do Swagger para uma exploração interativa ou do Postman. A coleção de requisições está disponível no diretório [`postman-collections`](/wells-store/postman-collections/).

Ao iniciar o módulo Wells Usuário, será realizado a migração do banco de dados e a inserção de dois usuários para testes.

| Usuário               | Senha  | Perfil |
|-----------------------|--------|--------|
| admin@wellsstore.br   | 123456 | ADMIN  |
| cliente@wellsstore.br | 123456 | CLIENTE|

### API de Pagamento

- **API**
  - `API Gateway:`: http://localhost:8125
  - `API Usuário:`: http://localhost:[porta aleatória definida pelo Spring Boot]
  
- **Swagger**
  - `API Gateway:` http://localhost:8125/swagger-ui.html`
  - `API Usuário:` http://localhost:[porta aleatória definida pelo Spring Boot]/wells-pagamento/swagger-ui.html`

#### Cria um novo pagamento

- `POST /api/v1/pagamentos`: Endpoint da API Wells Pagamento para Cria um novo pagamento.

1. `Endpoint`: /api/v1/pagamentos
2. `Método`: POST
3. **Autenticação:**
   - **Requer Token:** Sim

**Exemplo de requisição:**

curl:

```bash
curl -X 'POST' \
  'http://192.168.1.4:8082/api/v1/pagamentos' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "valor": 250,
  "nome": "Fernando Silva",
  "numero": "1234567890123456",
  "expiracao": "2024-05-31",
  "codigo": "123",
  "pedidoId": 10,
  "tipoCartao": "Visa"
}'
```

```json
    {
  "valor": 250,
  "nome": "Fernando Silva",
  "numero": "1234567890123456",
  "expiracao": "2024-05-31",
  "codigo": "123",
  "pedidoId": 10,
  "tipoCartao": "Visa"
}
```

Resposta:

```json
{
  "status": "OK",
  "data": {
    "id": 27,
    "valor": 250,
    "nome": "Fernando Silva",
    "numero": "1234567890123456",
    "expiracao": "2024-05-31",
    "codigo": "123",
    "pedidoId": 10,
    "status": "Criado",
    "tipoCartao": "Visa"
  }
}
```

#### Atualiza um pagamento existente

- `PATCH /api/v1/pagamentos/{id}`: Endpoint da API Wells Pagamento para Atualiza um pagamento existente.

1. `Endpoint`: /api/v1/pagamentos/{id}
2. `Método`: PATCH
3. **Autenticação:**
   - **Requer Token:** Sim

**Exemplo de requisição:**

curl:

```bash
curl -X 'PATCH' \
  'http://192.168.1.4:8082/api/v1/pagamentos/27' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "valor": 50,
  "nome": "Fernando Castro",
  "numero": "1234567890123456",
  "expiracao": "2023-12-31",
  "codigo": "123",
  "status": "Cancelado",
  "tipoCartao": "Elo"
}'
```

```json
{
  "valor": 50,
  "nome": "Fernando Castro",
  "numero": "1234567890123456",
  "expiracao": "2023-12-31",
  "codigo": "123",
  "status": "Cancelado",
  "tipoCartao": "Elo"
}    
```

Resposta:

```json
{
  "status": "OK",
  "data": {
    "id": 27,
    "valor": 50,
    "nome": "Fernando Castro",
    "numero": "1234567890123456",
    "expiracao": "2023-12-31",
    "codigo": "123",
    "pedidoId": 10,
    "status": "Criado",
    "tipoCartao": "Elo"
  }
}
```

#### Confirma um pagamento

- `PATCH /api/v1/pagamentos/{id}/confirmar`: Endpoint da API Wells Pagamento para Confirma um pagamento.

1. `Endpoint`: /api/v1/pagamentos/{id}/confirmar
2. `Método`: PATCH
3. **Autenticação:**
   - **Requer Token:** Sim

**Exemplo de requisição:**

curl:

```bash
curl -X 'PATCH' \
  'http://192.168.1.4:8082/api/v1/pagamentos/27/confirmar' \
  -H 'accept: */*'
```

#### Cancela um pagamento

- `DELETE /api/v1/pagamentos/{id}`: Endpoint da API Wells Pagamento para Cancela um pagamento.

1. `Endpoint`: /api/v1/pagamentos/{id}
2. `Método`: DELETE
3. **Autenticação:**
   - **Requer Token:** Sim

**Exemplo de requisição:**

curl:

```bash
curl -X 'DELETE' \
  'http://192.168.1.4:8082/api/v1/pagamentos/27' \
  -H 'accept: */*'
```
#### Consulta um pagamento por ID

- `GET /api/v1/pagamentos/{id}`: Endpoint da API Wells Pagamento para Consulta um pagamento por ID.

1. `Endpoint`: /api/v1/pagamentos/{id}
2. `Método`: GET
3. **Autenticação:**
   - **Requer Token:** Sim

**Exemplo de requisição:**

curl:

```bash
curl -X 'GET' \
  'http://192.168.1.4:8082/api/v1/pagamentos/27' \
  -H 'accept: */*'
```

Resposta:

```json
{
  "status": "OK",
  "data": {
    "id": 27,
    "valor": 50,
    "nome": "Fernando Castro",
    "numero": "1234567890123456",
    "expiracao": "2023-12-31",
    "codigo": "123",
    "pedidoId": 10,
    "status": "Cancelado",
    "tipoCartao": "Elo"
  }
}
```

#### Lista todos os pagamentos

- `GET /api/v1/pagamentos`: Endpoint da API Wells Pagamento para Lista todos os pagamentos.

1. `Endpoint`: /api/v1/pagamentos
2. `Método`: GET
3. **Autenticação:**
   - **Requer Token:** Sim

**Exemplo de requisição:**

curl:

```bash
curl -X 'GET' \
  'http://192.168.1.4:8082/api/v1/pagamentos?page=0&size=20' \
  -H 'accept: */*'
```

Resposta:

```json
{
  "status": "OK",
  "data": {
    "content": [
      {
        "id": 1,
        "valor": 100,
        "nome": "João Silva",
        "numero": "1234567890123456",
        "expiracao": "2024-04-30",
        "codigo": "123",
        "pedidoId": 1,
        "status": "Criado",
        "tipoCartao": "Visa"
      },
      {
        "id": 2,
        "valor": 150,
        "nome": "Maria Souza",
        "numero": "9876543210987654",
        "expiracao": "2024-05-31",
        "codigo": "456",
        "pedidoId": 2,
        "status": "Confirmado",
        "tipoCartao": "MasterCard"
      },
      {
        "id": 3,
        "valor": 75.5,
        "nome": "Carlos Ferreira",
        "numero": "5432167890123456",
        "expiracao": "2024-06-30",
        "codigo": "789",
        "pedidoId": 3,
        "status": "Cancelado",
        "tipoCartao": "Amex"
      },
      {
        "id": 4,
        "valor": 200,
        "nome": "Ana Santos",
        "numero": "1234987650123456",
        "expiracao": "2024-07-31",
        "codigo": "321",
        "pedidoId": 4,
        "status": "Criado",
        "tipoCartao": "Elo"
      },
      {
        "id": 6,
        "valor": 120,
        "nome": "Pedro Rocha",
        "numero": "1234789012345678",
        "expiracao": "2024-09-30",
        "codigo": "987",
        "pedidoId": 6,
        "status": "Cancelado",
        "tipoCartao": "Ticket Restaurante"
      },
      {
        "id": 7,
        "valor": 80,
        "nome": "Mariana Lima",
        "numero": "9876123450987609",
        "expiracao": "2024-10-31",
        "codigo": "654",
        "pedidoId": 7,
        "status": "Criado",
        "tipoCartao": "MasterCard Maestro"
      },
      {
        "id": 8,
        "valor": 300,
        "nome": "Lucas Vieira",
        "numero": "1234789012345612",
        "expiracao": "2024-11-30",
        "codigo": "987",
        "pedidoId": 8,
        "status": "Confirmado",
        "tipoCartao": "Visa Débito"
      },
      {
        "id": 9,
        "valor": 110,
        "nome": "Camila Costa",
        "numero": "9876123450987698",
        "expiracao": "2024-12-31",
        "codigo": "654",
        "pedidoId": 9,
        "status": "Cancelado",
        "tipoCartao": "Elo Débito"
      },
      {
        "id": 10,
        "valor": 180,
        "nome": "Rafaela Gomes",
        "numero": "1234789012345678",
        "expiracao": "2025-01-31",
        "codigo": "987",
        "pedidoId": 10,
        "status": "Criado",
        "tipoCartao": "Visa"
      },
      {
        "id": 11,
        "valor": 250,
        "nome": "Gustavo Almeida",
        "numero": "9876123450987698",
        "expiracao": "2025-02-28",
        "codigo": "654",
        "pedidoId": 11,
        "status": "Confirmado",
        "tipoCartao": "MasterCard"
      },
      {
        "id": 12,
        "valor": 95,
        "nome": "Aline Pereira",
        "numero": "1234789012345678",
        "expiracao": "2025-03-31",
        "codigo": "987",
        "pedidoId": 12,
        "status": "Cancelado",
        "tipoCartao": "Amex"
      },
      {
        "id": 13,
        "valor": 150,
        "nome": "Juliana Rodrigues",
        "numero": "9876123450987698",
        "expiracao": "2025-04-30",
        "codigo": "654",
        "pedidoId": 13,
        "status": "Criado",
        "tipoCartao": "Elo"
      },
      {
        "id": 14,
        "valor": 200,
        "nome": "Bruno Martins",
        "numero": "1234789012345678",
        "expiracao": "2025-05-31",
        "codigo": "987",
        "pedidoId": 14,
        "status": "Confirmado",
        "tipoCartao": "Alelo"
      },
      {
        "id": 15,
        "valor": 100,
        "nome": "Fábio Oliveira",
        "numero": "9876123450987698",
        "expiracao": "2025-06-30",
        "codigo": "654",
        "pedidoId": 15,
        "status": "Cancelado",
        "tipoCartao": "Ticket Restaurante"
      },
      {
        "id": 16,
        "valor": 175,
        "nome": "Patrícia Mendes",
        "numero": "1234789012345678",
        "expiracao": "2025-07-31",
        "codigo": "987",
        "pedidoId": 16,
        "status": "Criado",
        "tipoCartao": "MasterCard Maestro"
      },
      {
        "id": 17,
        "valor": 220,
        "nome": "Tatiane Sousa",
        "numero": "9876123450987698",
        "expiracao": "2025-08-31",
        "codigo": "654",
        "pedidoId": 17,
        "status": "Confirmado",
        "tipoCartao": "Visa Débito"
      },
      {
        "id": 18,
        "valor": 130,
        "nome": "Marcelo Barbosa",
        "numero": "1234789012345678",
        "expiracao": "2025-09-30",
        "codigo": "987",
        "pedidoId": 18,
        "status": "Cancelado",
        "tipoCartao": "Elo Débito"
      },
      {
        "id": 19,
        "valor": 190,
        "nome": "Eduarda Silva",
        "numero": "1234789012345678",
        "expiracao": "2025-10-31",
        "codigo": "987",
        "pedidoId": 19,
        "status": "Criado",
        "tipoCartao": "Visa"
      },
      {
        "id": 20,
        "valor": 270,
        "nome": "Roberto Santos",
        "numero": "9876123450987698",
        "expiracao": "2025-11-30",
        "codigo": "654",
        "pedidoId": 20,
        "status": "Confirmado",
        "tipoCartao": "MasterCard"
      },
      {
        "id": 21,
        "valor": 105,
        "nome": "Fabiana Oliveira",
        "numero": "1234789012345678",
        "expiracao": "2025-12-31",
        "codigo": "987",
        "pedidoId": 21,
        "status": "Cancelado",
        "tipoCartao": "Amex"
      }
    ],
    "pageable": {
      "pageNumber": 0,
      "pageSize": 20,
      "sort": {
        "empty": true,
        "sorted": false,
        "unsorted": true
      },
      "offset": 0,
      "paged": true,
      "unpaged": false
    },
    "last": false,
    "totalPages": 2,
    "totalElements": 28,
    "size": 20,
    "number": 0,
    "sort": {
      "empty": true,
      "sorted": false,
      "unsorted": true
    },
    "first": true,
    "numberOfElements": 20,
    "empty": false
  }
}
```
