![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Maven](https://img.shields.io/badge/maven-%23C71A36.svg?style=for-the-badge&logo=apache-maven&logoColor=white)
![Junit](https://img.shields.io/badge/junit-%23F7DF1E.svg?style=for-the-badge&logo=junit5&logoColor=black)
![Mockito](https://img.shields.io/badge/mockito-%23DA5B0B.svg?style=for-the-badge&logo=mockito&logoColor=white)
![AssertJ](https://img.shields.io/badge/assertj-%23F7DF1E.svg?style=for-the-badge&logo=java&logoColor=black)
![Git](https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white)


![GitHub language count](https://img.shields.io/github/languages/count/fsales/fiap-tech-chalenge-fase5)
![GitHub top language](https://img.shields.io/github/languages/top/fsales/fiap-tech-chalenge-fase5)
![GitHub code size in bytes](https://img.shields.io/github/languages/code-size/fsales/fiap-tech-chalenge-fase5)
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

- [Módulo Wells Core](#módulo-wells-core)
  - [Descrição do Projeto](#descrição-do-projeto)
  - [Clean Architecture](#clean-architecture)
  - [Estrutura do Projeto](#estrutura-do-projeto)
    - [Ambiente de Desenvolvimento](#ambiente-de-desenvolvimento)

# Módulo Wells Core

## Descrição do Projeto

Wells Core é um módulo do projeto Wells Store. Ele é responsável por prover as funcionalidades de domínio e regras de negócio do sistema.

A estrutura do projeto foi baseada na Clean Architecture, que é uma arquitetura de software que preza pela separação de responsabilidades.

## Clean Architecture

- [Clean Architecture](../README.md#clean-architecture)

## Estrutura do Projeto

A estrutura do projeto foi organizada de acordo com a Clean Architecture. A estrutura de pastas do projeto está organizada da seguinte forma:

```plaintext
├───wells-core
│   └───src
│       ├───main
│       │   └───java
│       │       └───br
│       │           └───com
│       │               └───wells
│       │                   └───core
│       │                       ├───domain
│       │                       │   ├───page
│       │                       │   └───usuario
│       │                       │       ├───exception
│       │                       │       ├───gateways
│       │                       │       ├───model
│       │                       │       │   └───enumeration
│       │                       │       └───usecases
│       │                       │           └───impl
│       │                       └───util
│       └───test
│           ├───java
│           │   └───br
│           │       └───com
│           │           └───wells
│           │               └───core
│           │                   └───domain
│           │                       └───usuario
│           │                           ├───model
│           │                           └───usecases
│           │                               └───impl
│           └───resources
```

### Ambiente de Desenvolvimento

Pré-requisitos e [configurações do ambiente de desenvolvimento](../README.md#configuração-do-ambiente-de-desenvolvimento).

- [Pré-requisitos](../README.md#pré-requisitos)
- [Realizar do clone do projeto](../README.md#realizar-do-clone-do-projeto)
- [GNU Make](../README.md#gnu-make)
  - [Construção e Execução](../README.md#construção-e-execução)