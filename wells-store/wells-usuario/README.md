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

- [Módulo Wells Usuário](#módulo-wells-usuário)
  - [Descrição do Projeto](#descrição-do-projeto)
  - [Clean Architecture](#clean-architecture)
  - [Estrutura do Projeto](#estrutura-do-projeto)
    - [Ambiente de Desenvolvimento](#ambiente-de-desenvolvimento)
      - [Configuração Comuns](#configuração-comuns)
      - [Banco de dados de desenvolvimento](#banco-de-dados-de-desenvolvimento)
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
  - [Endpoints do Módulo Wells Usuário](#endpoints-do-módulo-wells-usuário)
    - [Autenticação](#autenticação)
    - [Cadastrar Usuário](#cadastrar-usuário)
    - [Alterar Senha do Usuário](#alterar-senha-do-usuário)
    - [Listar Usuário](#listar-usuário)
    - [Listar Usuário por ID](#listar-usuário-por-id)
  - [Referência Bibliográfica](#referência-bibliográfica)

# Módulo Wells Usuário

## Descrição do Projeto

Este módulo é parte do projeto Wells Store, que é uma aplicação que utiliza a arquitetura Clean Architecture para fornecer uma estrutura modular e organizada.

O módulo Wells Usuário é responsável por prover as funcionalidades de usuário do sistema.

## Clean Architecture

- [Clean Architecture](../README.md#clean-architecture)

## Estrutura do Projeto

A estrutura do projeto foi organizada de acordo com a Clean Architecture. A estrutura de pastas do projeto está organizada da seguinte forma:

```plaintext
└───wells-usuario
├───postman-collections
└───src
    ├───main
    │   ├───generated
    │   ├───java
    │   │   └───br
    │   │       └───com
    │   │           └───wells
    │   │               └───app
    │   │                   ├───infrastructure
    │   │                   │   ├───database
    │   │                   │   │   └───postgres
    │   │                   │   │       ├───entity
    │   │                   │   │       │   └───listener
    │   │                   │   │       └───repository
    │   │                   │   ├───gateways
    │   │                   │   │   └───usuario
    │   │                   │   │       └───mapper
    │   │                   │   └───spring
    │   │                   │       ├───config
    │   │                   │       │   ├───app
    │   │                   │       │   ├───database
    │   │                   │       │   │   └───jpa
    │   │                   │       │   ├───security
    │   │                   │       │   └───swagger
    │   │                   │       └───security
    │   │                   │           ├───jwt
    │   │                   │           │   ├───exception
    │   │                   │           │   └───impl
    │   │                   │           └───user
    │   │                   │               └───impl
    │   │                   └───presentation
    │   │                       ├───exception
    │   │                       └───rest
    │   │                           ├───controller
    │   │                           │   ├───auth
    │   │                           │   │   ├───dto
    │   │                           │   │   │   ├───request
    │   │                           │   │   │   └───response
    │   │                           │   │   └───swagger
    │   │                           │   └───usuario
    │   │                           │       ├───dto
    │   │                           │       │   ├───mapper
    │   │                           │       │   ├───request
    │   │                           │       │   │   └───swagger
    │   │                           │       │   └───response
    │   │                           │       └───swagger
    │   │                           └───validation
    │   └───resources
    │       └───db
    │           └───changelog
    │               └───changes
    │                   └───postgres
    └───test
        └───resources
```

### Ambiente de Desenvolvimento

Pré-requisitos e [configurações do ambiente de desenvolvimento](../README.md#configuração-do-ambiente-de-desenvolvimento).

#### Configuração Comuns

- [Pré-requisitos](../README.md#pré-requisitos)
- [Realizar do clone do projeto](../README.md#realizar-do-clone-do-projeto)
- [GNU Make](../README.md#gnu-make)
  - [Construção e Execução](../README.md#construção-e-execução)

#### Banco de dados de desenvolvimento

Para iniciar o banco de desenvolvimento, execute os passos descrito no arquivo [Executar docker-compose do banco Postgres de desenvolvimento para wells-usuario](/wells-store/README.md#executar-docker-compose-do-banco-postgres-de-desenvolvimento-para-wells-usuario).

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
- `V1__CREATE_USUARIO.sql`: Efetua a criação da tabela `usuario`.
- `V2__TRIGGER.sql`: Encarrega-se da criação do trigger para a tabela `usuario`.
- `V3__DML.sql`: Realiza a inserção de dados na tabela `usuario`.

#### Arquivo `.env`

O arquivo `.env` é utilizado para armazenar informações sensíveis, como chaves e senhas, separando esses dados do código-fonte. Isso facilita a configuração do software em diferentes ambientes, embora seja importante notar que o `.env` por si só não garante total segurança.

No projeto Wells Usuário, o arquivo `.env` está localizado no diretório `wells-store/wells-usuario` e contém as variáveis de ambiente necessárias para a execução do módulo.

O arquivo está disponível no repositório do projeto através do link [`.env`](.env).

## Endpoints do Módulo Wells Usuário

Nesta seção, apresentamos uma lista de endpoints disponíveis no módulo Wells Usuário, todos devidamente documentados no Swagger. Você pode acessar a documentação por meio do link [Swagger](http://localhost:8081/docs-wells-usuario.html).

Para realizar requisições HTTP, sugerimos a utilização do Swagger para uma exploração interativa ou do Postman, onde a coleção de requisições está disponível no diretório [`postman-collections`](postman-collections/).

A lista de endpoints disponíveis no módulo Wells Usuário inclui:

### Autenticação

- `POST /api/v1/auth/login`: Endpoint da API Wells Usuário para autenticação de usuários.

### Cadastrar Usuário

- `POST /api/v1/usuarios`: Endpoint da API Wells Usuário para registro de novos usuários.

### Alterar Senha do Usuário

- `PATCH /api/v1/usuarios/{id}/senha`: Endpoint da API Wells Usuário para alteração da senha do usuário.

### Listar Usuário

- `GET /api/v1/usuarios`: Endpoint da API Wells Usuário para listar todos os usuários.

### Listar Usuário por ID

- `GET /api/v1/usuarios/{id}`: Endpoint da API Wells Usuário para listar um usuário específico por ID.


## Referência Bibliográfica

[^1]: [Documentação Oficial do Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/html/)

[^2]: [Documentação do Liquibase](https://docs.liquibase.com/)