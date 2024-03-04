![GitHub](https://img.shields.io/github/license/fsales/fiap-tech-chalenge-fase5)
![GitHub code size in bytes](https://img.shields.io/github/languages/code-size/fsales/fiap-tech-chalenge-fase5)
![GitHub language count](https://img.shields.io/github/languages/count/fsales/fiap-tech-chalenge-fase5)
![GitHub top language](https://img.shields.io/github/languages/top/fsales/fiap-tech-chalenge-fase5)


# Projeto Wells

## Descrição do Projeto

O Projeto Wells é uma aplicação que utiliza a arquitetura Clean Architecture para fornecer uma estrutura modular e organizada. Este README fornece informações sobre a estrutura do projeto, sua arquitetura, configurações do ambiente de desenvolvimento.

## Estrutura do Projeto

- **wells-core:** Estrutura da camada de domínio, classes principais e princípios do Clean Architecture.
- **wells-usuario:** Módulo de usuário com camadas de apresentação, infraestrutura e domínio.

## Configuração do Ambiente de Desenvolvimento

### Pré-requisitos

- Java 17 ou superior
- Gradle
- Docker

### Configuração do Banco de Dados

1. Inicialize o banco de dados usando o seguinte comando:
    ```bash
    docker-compose up -d
    ```
2. Execute os scripts de criação de tabelas e dados iniciais do diretório `src