![GitHub](https://img.shields.io/github/license/fsales/fiap-tech-chalenge-fase5)
![GitHub code size in bytes](https://img.shields.io/github/languages/code-size/fsales/fiap-tech-chalenge-fase5)
![GitHub language count](https://img.shields.io/github/languages/count/fsales/fiap-tech-chalenge-fase5)
![GitHub top language](https://img.shields.io/github/languages/top/fsales/fiap-tech-chalenge-fase5)

- [Projeto Wells](#projeto-wells)
  - [Descrição do Projeto](#descrição-do-projeto)
  - [Estrutura do Projeto](#estrutura-do-projeto)
  - [Configuração do Ambiente de Desenvolvimento](#configuração-do-ambiente-de-desenvolvimento)
    - [Pré-requisitos](#pré-requisitos)
    - [Realizar do clone do projeto](#realizar-do-clone-do-projeto)
    - [GNU Make](#gnu-make)
    - [Banco de Dados](#banco-de-dados)

# Projeto Wells

## Descrição do Projeto

O Projeto Wells é uma aplicação que utiliza a arquitetura Clean Architecture para fornecer uma estrutura modular e organizada. Este README fornece informações sobre a estrutura do projeto, sua arquitetura, configurações do ambiente de desenvolvimento.

## Estrutura do Projeto

O projeto foi dividido em módulos para facilitar a manutenção e a escalabilidade. Cada módulo possui sua própria estrutura de diretórios e arquivos, incluindo um arquivo `Makefile` para automatizar a compilação e execução do projeto.

A estrutura do projeto é a seguinte:

```plaintext
└───wells-store
    │   
    ├───wells-core
    │   
    └───wells-usuario
```

- **wells-store:** Projeto principal que contém os módulos do projeto.
  - **wells-core:** Estrutura da camada de domínio, classes principais e princípios do Clean Architecture.
  - **wells-usuario:** Módulo de usuário com camadas de apresentação, infraestrutura e domínio.

## Configuração do Ambiente de Desenvolvimento

### Pré-requisitos

- Java 17 ou superior
- Maven
- Gnu Make
- Docker

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

Para facilitar o processo de compilação e execução do projeto, foram criados arquivos `Makefile` para cada módulo do projeto. Esses arquivos contêm regras para compilar e executar o projeto.

- [Wells Core Makefile](make-wells-core.mk)
  
**Passos para Executar a Partir do módulo `wells-store`**

1. **Construir o Projeto Java:**
   - Abra um terminal e navegue até o diretório `wells-store`.
   - Execute o seguinte comando para construir, verificar e empacotar o projeto Java:

    ```bash
    make -f make-wells-core.mk java_build
    ```

2. **Instalar Artefatos Maven:**
   - Ainda no terminal no diretório `wells-store`, execute o seguinte comando para instalar os arquivos JAR no repositório local Maven:

    ```bash
    make -f make-wells-core.mk java_install
    ```

3. **Limpar Artefatos de Construção:**
   - Para limpar os artefatos de construção, use o seguinte comando:

    ```bash
    make -f make-wells-core.mk clean
    ```

4. **Executar Todas as Metas:**
   - Se você deseja executar todas as metas consecutivamente, pode usar o seguinte comando:

     ```bash
     make -f  make-wells-core.mk all
     ```

- [Wells Usuário Makefile](make-wells-usuario.mk)

**Passos para Executar a Partir do módulo `wells-store`**

1. **Construir o Projeto Java:**
   - Abra um terminal e navegue até o diretório `wells-usuario`.
   - Execute o seguinte comando para construir, verificar e empacotar o projeto Java:

    ```bash
    make -f make-wells-usuario.mk java_build
    ```

2. **Instalar Artefatos Maven:**
   - Ainda no terminal no diretório `wells-usuario`, execute o seguinte comando para instalar os arquivos JAR no repositório local Maven:

    ```bash
    make -f make-wells-usuario.mk java_install
    ```

3. **Construir a Imagem Docker e Executar docker-compose para wells-usuario:**
   - Execute os seguintes comandos para construir a imagem Docker e iniciar o serviço usando docker-compose:

    ```bash
    make -f make-wells-usuario.mk docker_build
    make -f make-wells-usuario.mk docker_compose_up_wells_usuario
    ```

4. **Parar docker-compose para wells-usuario:**
   - Caso seja necessário parar o serviço, execute o seguinte comando:

    ```bash
    make -f make-wells-usuario.mk docker_compose_down_wells_usuario
    ```

5. **Parar docker-compose removendo volumes:**
   - Se desejar parar o serviço e remover os volumes, use o comando a seguir:

    ```bash
    make -f make-wells-usuario.mk docker_compose_down_wells_usuario_remove_volumes
    ```

6. **Limpar Artefatos de Construção:**
   - Para limpar todos os artefatos de construção, incluindo a imagem Docker, utilize o seguinte comando:

    ```bash
    make -f make-wells-usuario.mk clean
    ```

Estes comandos devem ser executados no diretório `wells-store`, onde o arquivo `make-wells-usuario.mk` está localizado e onde o projeto `wells-store` está armazenado. Certifique-se de ajustar o diretório conforme necessário antes de executar os comandos.

### Banco de Dados

Foi escolhido o banco de dados Postgres para armazenar os dados da aplicação. O banco de dados é executado em um contêiner Docker, que pode ser iniciado com o comando descrito em [Docker](../docker/README.md).
