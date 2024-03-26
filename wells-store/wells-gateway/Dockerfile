# Use a imagem base do Amazon Corretto
FROM public.ecr.aws/amazoncorretto/amazoncorretto:17

# Argumentos
ARG USER_UID="1000" \
ARG USER_GID="1000"
ARG USER_NAME="wells.gateway"
ARG JAR_FILE=/target/wells-gateway.jar
ARG VERSION="1.0"

# Variáveis de ambiente
ENV PORT=8080 \
    DEPENDENCY=/app

# Metadados da imagem
LABEL maintainer="Well Store - Gatawey" \
      version=${VERSION} \
      description="Gateway da aplicação Well Store."

# Instalação de pacotes necessários
RUN yum install -y shadow-utils

# Definição do diretório de trabalho
WORKDIR /app

# Criação de usuário não-root
RUN groupadd -g $USER_GID $USER_NAME && \
    useradd -m -g $USER_GID -u $USER_UID $USER_NAME

# Troca para usuário não-root
USER $USER_UID:$USER_GID

# Copia o arquivo JAR para o contêiner
COPY ${JAR_FILE} ${DEPENDENCY}/app.jar

# Definição do comando de inicialização do contêiner
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar ${DEPENDENCY}/app.jar ${0} ${@}"]

# Exposição da porta
#EXPOSE ${PORT}
