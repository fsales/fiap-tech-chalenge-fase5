
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

- [:bookmark\_tabs: Tech Chalenge](#bookmark_tabs-tech-chalenge)
  - [🛠️ Linguagem e ferramentas](#️-linguagem-e-ferramentas)
  - [🏫 Dados Acadêmicos](#-dados-acadêmicos)
    - [🏬 Instituição](#-instituição)
    - [🧑🏻‍🎓Curso](#curso)
    - [Aluno(s)](#alunos)
  - [Pré-requisitos](#pré-requisitos)
  - [Clean Architecture](#clean-architecture)
    - [Princípios da Clean Architecture](#princípios-da-clean-architecture)
    - [Estrutura do Projeto](#estrutura-do-projeto)
  - [Módulos](#módulos)
  - [Referência Bibliográfica](#referência-bibliográfica)

# :bookmark_tabs: Tech Chalenge

O Projeto Wells é uma aplicação que utiliza a arquitetura Clean Architecture para fornecer uma estrutura modular e organizada. Este README fornece informações sobre a estrutura do projeto, sua arquitetura, configurações do ambiente de desenvolvimento.


## 🛠️ Linguagem e ferramentas

[![logo java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=kofi&logoColor=white)](#)

![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)

![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)

[![logo spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/)
[![logo spring-boot](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot)](https://spring.io/projects/spring-boot)

[![logo git](https://img.shields.io/badge/GIT-E44C30?style=for-the-badge&logo=git&logoColor=white)](https://git-scm.com/)
[![logo github](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com)

[![logo eclipse](https://img.shields.io/badge/Eclipse-2C2255?style=for-the-badge&logo=eclipse&logoColor=white)](https://www.eclipse.org/)
[![logo intellij](https://img.shields.io/badge/IntelliJ_IDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)](https://www.jetbrains.com/pt-br/idea/)

[![logo curl](https://img.shields.io/badge/curl-093754.svg?style=for-the-badge&logo=curl-idea&logoColor=white)](https://curl.se/)
[![logo Google-chrome](https://img.shields.io/badge/Google_chrome-4285F4?style=for-the-badge&logo=Google-chrome&logoColor=white)](https://www.google.pt/intl/pt-PT/chrome/?brand=FHFK&gclid=CjwKCAjwnOipBhBQEiwACyGLuu4mCvDZcz9NfyfYpBcLdbDQXuIG2WbyC85RYuP3SLSiNGUcNE9hyRoCXxkQAvD_BwE&gclsrc=aw.ds)
[![logo postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=Postman&logoColor=white)](https://www.postman.com/)
[![logo swagger](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=Swagger&logoColor=white)](https://swagger.io/)

## 🏫 Dados Acadêmicos

### 🏬 Instituição

Faculdade FIAP

### 🧑🏻‍🎓Curso

Pós-Graduação em Arquitetura e Desenvolvimento Java

### Aluno(s)

## Pré-requisitos

Antes de começar, você precisará ter as seguintes ferramentas instaladas em sua máquina:

- Java 17
- Git
- Docker
- GNU Make
- IDE de desenvolvimento:
  - Eclipse
  - IntelliJ IDEA
  - VSCode
  - Outras
- Navegador:
  - Google Chrome
  - Outro
- Postman, CURL ou utilize o Swagger

## Clean Architecture

### Princípios da Clean Architecture

A Clean Architecture[^1] é baseada em alguns princípios fundamentais para garantir a separação de preocupações e a manutenibilidade do código. Os principais princípios incluem:

1. **Independência de Frameworks:** As camadas internas não devem depender de nenhum framework externo, permitindo a fácil substituição de tecnologias.

2. **Independência de Detalhes:** As camadas internas não devem depender de detalhes de implementação, garantindo flexibilidade na evolução do sistema.

3. **Modelo de Domínio Puro:** As entidades e regras de negócio residem no núcleo da aplicação, mantendo-se independente de frameworks e detalhes externos.

4. **Separação de Responsabilidades:** As camadas são organizadas de maneira a separar as responsabilidades, proporcionando uma visão clara e escalável do sistema.

### Estrutura do Projeto

A implementação da Clean Architecture no Projeto Wells segue a seguinte estrutura:

- **Entidades e Casos de Uso:** O núcleo da aplicação contém as entidades de domínio e os casos de uso que representam as operações principais.

- **Gateways e Adaptadores:** A camada externa conecta-se ao núcleo através de gateways que definem interfaces. Os adaptadores implementam essas interfaces, lidando com detalhes externos.

- **Frameworks e Drivers:** A camada mais externa contém os frameworks e drivers, interagindo diretamente com a infraestrutura.

## Módulos

- [wells-store](wells-store/README.md#descrição-do-projeto)
  - [wells-core](wells-store/wells-core/README.md)
  - [wells-usuario](wells-store/wells-usuario/README.md)

## Referência Bibliográfica

  [^1]:[Uncle Bob - The Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)