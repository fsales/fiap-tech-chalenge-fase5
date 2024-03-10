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
    - [Configuração do Ambiente de Desenvolvimento](#configuração-do-ambiente-de-desenvolvimento)
    - [Construção e Execução](#construção-e-execução)
    - [Arquivos de Configuração](#arquivos-de-configuração)

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

### Configuração do Ambiente de Desenvolvimento

Pré-requisitos e configurações do ambiente de desenvolvimento.

### Construção e Execução

Instruções para construir e executar o módulo.

### Arquivos de Configuração

Detalhes sobre os arquivos de configuração.

