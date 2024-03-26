
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


- [Projeto Wells](#projeto-wells)
  - [Descrição do Projeto](#descrição-do-projeto)
  - [Estrutura do Projeto](#estrutura-do-projeto)
  - [Configuração do Ambiente de Desenvolvimento](#configuração-do-ambiente-de-desenvolvimento)
    - [Pré-requisitos](#pré-requisitos)
    - [Realizar do clone do projeto](#realizar-do-clone-do-projeto)
    - [GNU Make](#gnu-make)
      - [Construção e Execução](#construção-e-execução)
        - [Wells Store Makefile](#wells-store-makefile)
          - [Construir o Projeto Java](#construir-o-projeto-java)
          - [Instalar Artefatos Maven](#instalar-artefatos-maven)
          - [Construir imagem Docker](#construir-imagem-docker)

# Projeto Wells

## Descrição do Projeto

O Projeto Wells é uma aplicação que utiliza a arquitetura Clean Architecture para fornecer uma estrutura modular e organizada. Este README fornece informações sobre a estrutura do projeto, sua arquitetura, configurações do ambiente de desenvolvimento.

## Estrutura do Projeto

O projeto foi dividido em módulos para facilitar a manutenção e a escalabilidade. Cada módulo possui sua própria estrutura de diretórios e arquivos, incluindo um arquivo `Makefile` para automatizar a compilação e execução do projeto.

A estrutura do projeto é a seguinte:

```plaintext
└───wells-store
    │
    ├───wells-carrinho
    │
    ├───wells-core
    │
    ├───wells-gateway
    │
    ├───wells-pagamento
    │
    ├───wells-produto
    │
    └───wells-usuario
```

- **[wells-store:](/wells-store/README.md)** Projeto principal que contém os módulos do projeto.
  - **[Wells Carrinho:](/wells-store/wells-carrinho/README.md)** Módulo de gerenciamento de carrinho.
  - **[wells-core:](/wells-store/wells-core/README.md)** Estrutura da camada de domínio, classes principais e princípios do Clean Architecture.
  - **[Wells Pagamento:](/wells-store/wells-pagamento/README.md)** Módulo de gerenciamento de pagamentos.
  - **[Wells Produto:](/wells-store/wells-produto/README.md)**  Módulo de gerenciamento de produtos.
  - **[wells-usuario:](/wells-store/wells-usuario/README.md)** Módulo de gerenciamento de usuários.
  
- **Ferramentas de Integração:** Ferramentas utilizadas para integração entre os módulos.
  - **[Wells Gateway:](/wells-store/wells-gateway/README.md)** Atua como ponto de entrada único para a aplicação, fornecendo interface para comunicação com os microserviços.
  - **Consul:** Sistema de descoberta de serviços que permite aos microserviços se registrarem e localizarem outros serviços quando necessário.
  - **RabbitMQ:** Utilizado na integração entre o serviço de carrinho (wells-carrinho) e o de pagamento (wells-pagamento), garantindo uma comunicação eficiente entre eles.
  

## Configuração do Ambiente de Desenvolvimento

### Pré-requisitos

- Java 17 ou superior
- Maven
- Gnu Make
- Docker
- Git

- Aplicações que devem ser executadas no container Docker:
  - PostgreSQL
  - RabbitMQ
  - Consul

* No diretorio **[wells-store](/wells-store/)** tem um **[docker-compose.yml](/wells-store/docker-compose.yaml)** que pode ser executado para testar a aplicação

### Realizar do clone do projeto

1. Abrir o terminal e navegar até o diretório onde o projeto será clonado.

**Terminal:**

- Git Bash
- CMD
- Bash
- Outros

Exemplo de como navegar até o diretório onde o projeto será clonado:

```bash
cd /c/projetos/
```

2. Clonar o repositório do projeto e exe.

```bash
git clone https://github.com/fsales/fiap-tech-chalenge-fase5.git fiap-tech-chalenge-fase5
```

3. Navegar até o diretório do projeto.

```bash
cd fiap-tech-chalenge-fase5/wells-store
```

4. Verificar se o diretório do projeto foi clonado corretamente.

```bash
ls
```

5.Executar os estágios do [Make](https://github.com/fsales/fiap-tech-chalenge-fase5/blob/feature/usuario/wells-store/README.md#gnu-make) ou faça importação na sua IDE de preferência.

### GNU Make

O GNU Make é uma ferramenta de automação de compilação amplamente utilizada em sistemas Unix e Unix-like. Ela permite a definição de regras para compilar e construir programas, facilitando o processo de desenvolvimento de software. O Makefile é um arquivo de configuração que contém instruções para o Make sobre como compilar e construir o projeto.

1. Instação do GNU Make no Windows:

```bash
choco install make
```

2. Instalação do GNU Make no MacOS:

```bash
brew install make
```

#### Construção e Execução

Para facilitar o processo de compilação e execução do projeto, foram criados arquivos `Makefile` para cada módulo do projeto. Esses arquivos contêm regras para compilar e executar o projeto.

##### [Wells Store Makefile](/make-wells-store.mk)

**Passos para Executar a Partir do diretorio  `raiz`**

###### Construir o Projeto Java

- Abra um terminal e navegue até o diretório `raiz` (diretorio que foi realizado o clone do projeto).
- Execute o seguinte comando para construir, verificar e empacotar o projeto Java:

```bash
make -f make-wells-store java_package
```

###### Instalar Artefatos Maven

- Ainda no terminal no diretório `raiz`, execute o seguinte comando para instalar os arquivos JAR no repositório local Maven:

```bash
make -f make-wells-store java_install
```

###### Construir imagem Docker

```bash
make -f make-wells-store docker_build_all
```