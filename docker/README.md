<p align="center">
 <img src="https://img.shields.io/static/v1?label=GitHub&message=@Wells-store&color=8257E5&labelColor=000000" alt="@wells-store" />
 <img src="https://img.shields.io/static/v1?label=Tipo&message=Tech%20Chalenge&color=8257E5&labelColor=000000" alt="Tech Chalenge" />
</p>

[![logo docker](https://img.shields.io/badge/Docker-2CA5E0?style=for-the-badge&logo=docker&logoColor=white)](https://docker.com)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)

- [Docker](#docker)
  - [Docker Compose](#docker-compose)
  - [Docker Compose do Postgres](#docker-compose-do-postgres)
  - [Docker Compose dos Módulos do Wells-store](#docker-compose-dos-módulos-do-wells-store)
    - [Wells Usuário](#wells-usuário)
  - [Referência Bibliográfica](#referência-bibliográfica)

# Docker

O Docker é uma plataforma de software que permite criar, implantar e executar aplicativos em contêineres. Os contêineres são unidades de software leves e portáteis que incluem tudo o que é necessário para executar um aplicativo, incluindo código, bibliotecas, ferramentas e dependências. [^1] [^2] [^3]

- **Contêineres**: Os contêineres Docker fornecem uma maneira consistente de desenvolver, empacotar e implantar aplicativos, garantindo que eles sejam executados da mesma forma em qualquer ambiente.

- **Imagens**: As imagens Docker são a base dos contêineres. Elas contêm o código-fonte, as bibliotecas e todas as dependências necessárias para executar um aplicativo.

- **Dockerfile**: Um Dockerfile é um arquivo de configuração usado para definir uma imagem Docker. Ele especifica as instruções necessárias para montar a imagem, como instalar pacotes, copiar arquivos e configurar o ambiente.

- **Docker Hub**: O Docker Hub é um registro público de imagens Docker que permite aos desenvolvedores compartilhar e distribuir suas imagens. Ele contém milhares de imagens prontas para uso em uma variedade de tecnologias e aplicativos.

## Docker Compose

O Docker Compose é uma ferramenta que permite definir e executar aplicativos Docker de vários contêineres. Com ele, você pode definir a configuração de vários serviços, incluindo imagens Docker, variáveis de ambiente, redes e volumes, em um único arquivo YAML. [^4] [^5] [^6]

- **Definição de Serviços**: O Docker Compose permite definir a configuração de vários serviços em um único arquivo YAML. Isso inclui imagens Docker, variáveis de ambiente, comandos de inicialização, redes e volumes.

- **Gerenciamento de Recursos**: Você pode iniciar, parar e reiniciar todos os serviços definidos no arquivo Docker Compose usando comandos simples.

- **Reutilização de Configuração**: O arquivo Docker Compose pode ser compartilhado entre membros da equipe, facilitando a padronização da configuração de desenvolvimento e implantação.

## Docker Compose do Postgres

O arquivo `docker-compose-postgres.yaml` foi configurado de forma genérica para ser utilizado no desenvolvimento de qualquer módulo do Wells-store. No entanto, é importante observar que na definição do `docker compose`, não foram especificados volumes e rede. Para utilizá-lo, é necessário apenas definir as seguintes variáveis de ambiente:

- `POSTGRES_DB`
- `POSTGRES_USER`
- `POSTGRES_PASSWORD`
- `POSTGRES_CONTAINER_NAME`

Você pode encontrar o arquivo `docker-compose-postgres.yaml` [aqui](docker-compose-postgres.yaml).

- Exemplo de como criar o arquivo [`env/.env-wells-usuario-db`](env/.env-wells-usuario-db) para iniciar  o `docker-compose-postgres.yaml` .

```text
POSTGRES_DB=wells-usuario
POSTGRES_USER=postgres
POSTGRES_PASSWORD=postgres
POSTGRES_CONTAINER_NAME=wells-usuario-db
```

- Exemplo de como iniciar o container do Postgres.

```bash
docker-compose -f docker-compose-postgres.yaml --env-file env/.env-wells-usuario-db up -d --force-recreate --remove-orphans
```

- Exemplo de como parar o container do Postgres.

```bash
docker-compose -f docker-compose-postgres.yaml --env-file env/.env-wells-usuario-db down
```

## Docker Compose dos Módulos do Wells-store

Nesta sessão, serão apresentados os arquivos `docker-compose` de cada módulo do Wells-store. Cada arquivo foi configurado para utilizar a imagem dos seus respectivos módulos. Para utilizá-los, é necessário realizar o build das imagens de cadas modulo e para facilitar esse processo foi criar um arquivo `Makefile` para cada módulo.

Nesta seção, apresentamos os arquivos `docker-compose` de cada módulo do Wells-store. O Wells-store é um conjunto de módulos de um sistema de gerenciamento de lojas online. Cada arquivo `docker-compose` foi configurado para utilizar a imagem correspondente ao seu respectivo módulo, facilitando o desenvolvimento e a execução do sistema como um todo.

Para utilizar os arquivos `docker-compose`, é necessário realizar o build das imagens de cada módulo. Para tornar esse processo mais simples e automatizado, foi criado um arquivo `Makefile` para cada módulo.

>Make e Makefile:
Make é uma ferramenta de automação de compilação amplamente utilizada em sistemas Unix e Unix-like. Ela permite a definição de regras para compilar e construir programas, facilitando o processo de desenvolvimento de software. O Makefile é um arquivo de configuração que contém instruções para o Make sobre como compilar e construir o projeto. [^7]

Cada arquivo `docker-compose` pode conter configurações específicas para o módulo correspondente, como definições de rede, variáveis de ambiente e volumes montados. Certifique-se de revisar essas configurações antes de iniciar os serviços.

Por exemplo, o arquivo `docker-compose-postgres.yaml` foi configurado para iniciar o container do Postgres de forma genérica. Ele requer apenas a definição das variáveis de ambiente:

- `POSTGRES_DB`
- `POSTGRES_USER`
- `POSTGRES_PASSWORD`
- `POSTGRES_CONTAINER_NAME`

Essas variáveis permitem personalizar o ambiente do banco de dados de acordo com as necessidades do projeto.

### Wells Usuário

O arquivo `docker-compose-wells-usuario.yaml` foi configurado para subir o container do Wells Usuário. Para utilizá-lo, é necessário apenas definir as seguintes variáveis de ambiente:

Variaveis de ambiente da API Wells Usuário:

- `PORT`
- `PROFILE_ENVIRONMENT`
- `WELLS_USUARIO_SPRING_DATASOURCE_URL`
- `WELLS_USUARIO_SPRING_DATASOURCE_USERNAME`
- `WELLS_USUARIO_SPRING_DATASOURCE_PASSWORD`
- `WELLS_USUARIO_JWT_SECRET`

Variaveis de ambiente do banco de dados:

- `POSTGRES_DB`
- `POSTGRES_USER`
- `POSTGRES_PASSWORD`

Você pode encontrar o arquivo `docker-compose-wells-usuario.yaml` [aqui](docker-compose-wells-usuario.yaml).

- Exemplo de como criar o arquivo [`env/.env-wells-usuario`](env/.env-wells-usuario) para iniciar  o `docker-compose-wells-usuario.yaml` .

```text
PORT=8081
PROFILE_ENVIRONMENT=dev
WELLS_USUARIO_SPRING_DATASOURCE_URL=jdbc:postgresql://wells-usuario-db:5432/wells-usuario
WELLS_USUARIO_SPRING_DATASOURCE_USERNAME=postgres
WELLS_USUARIO_SPRING_DATASOURCE_PASSWORD=postgres
WELLS_USUARIO_JWT_SECRET=413F4428472B4B6250655368566D5970337336763979244226452948404D6351
```

- Exemplo de como iniciar o container do Wells Usuário.

```bash
docker-compose -f docker-compose-wells-usuario.yaml --env-file env/.env-wells-usuario up -d --force-recreate --remove-orphans
```

- Exemplo de como parar o container do Wells Usuário.

```bash
docker-compose -f docker-compose-wells-usuario.yaml --env-file env/.env-wells-usuario down
```

## Referência Bibliográfica

  [^1]: [Documentação Oficial do Docker](https://docs.docker.com/)

  [^2]: [Tutorial do Docker no Docker Docs](https://docs.docker.com/get-started/)

  [^3]: [Docker no GitHub](https://github.com/docker)

  [^4]: [Documentação Oficial do Docker Compose](https://docs.docker.com/compose/)

  [^5]: [Tutorial do Docker Compose no Docker Docs](https://docs.docker.com/compose/gettingstarted/)

  [^6]: [Docker Compose no GitHub](https://github.com/docker/compose)
  
  [^7]: [Documentação Oficial do Make](https://www.gnu.org/software/make/manual/make.html)