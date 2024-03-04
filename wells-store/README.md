![GitHub](https://img.shields.io/github/license/fsales/fiap-tech-chalenge-fase5)
![GitHub code size in bytes](https://img.shields.io/github/languages/code-size/fsales/fiap-tech-chalenge-fase5)
![GitHub language count](https://img.shields.io/github/languages/count/fsales/fiap-tech-chalenge-fase5)
![GitHub top language](https://img.shields.io/github/languages/top/fsales/fiap-tech-chalenge-fase5)


# Projeto Wells Store

Bem-vindo ao Projeto Wells! Este repositório contém a implementação de um sistema utilizando a arquitetura Clean Architecture. Cada módulo do projeto é detalhado abaixo, e a arquitetura Clean Architecture é explicada em documentos individuais.

## Clean Architecture

### Princípios da Clean Architecture

A Clean Architecture é baseada em alguns princípios fundamentais para garantir a separação de preocupações e a manutenibilidade do código. Os principais princípios incluem:

1. **Independência de Frameworks:** As camadas internas não devem depender de nenhum framework externo, permitindo a fácil substituição de tecnologias.

2. **Independência de Detalhes:** As camadas internas não devem depender de detalhes de implementação, garantindo flexibilidade na evolução do sistema.

3. **Modelo de Domínio Puro:** As entidades e regras de negócio residem no núcleo da aplicação, mantendo-se independente de frameworks e detalhes externos.

4. **Separação de Responsabilidades:** As camadas são organizadas de maneira a separar as responsabilidades, proporcionando uma visão clara e escalável do sistema.

### Estrutura do Projeto

A implementação da Clean Architecture no Projeto Wells segue a seguinte estrutura:

- **Entidades e Casos de Uso:** O núcleo da aplicação contém as entidades de domínio e os casos de uso que representam as operações principais.

- **Gateways e Adaptadores:** A camada externa conecta-se ao núcleo através de gateways que definem interfaces. Os adaptadores implementam essas interfaces, lidando com detalhes externos.

- **Frameworks e Drivers:** A camada mais externa contém os frameworks e drivers, interagindo diretamente com a infraestrutura.

### Referências

- [Uncle Bob - The Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)

## Módulos

- [wells-core](wells-core/README.md)
- [wells-usuario](wells-usuario/README.md)
