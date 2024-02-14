# Stage 1: Build
FROM maven:3-amazoncorretto-17 as builder

WORKDIR /app

# Copie apenas os arquivos necessários para evitar cópias desnecessárias
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src
RUN chmod +x mvnw # Dar permissões de execução ao arquivo mvnw
# Execute os comandos Maven
RUN mvn -Dmaven.test.skip=true clean package && \
    cd target && \
    java -Djarmode=layertools -jar Wellsmart.jar extract

# Stage 2: Runtime
FROM public.ecr.aws/amazoncorretto/amazoncorretto:17
RUN yum install -y shadow-utils

WORKDIR /app

# Crie o grupo e o usuário sem depender da ferramenta "spring"
RUN groupadd --system --gid 3000 spring \
    && useradd --system --uid 1000 --shell /bin/false --gid spring spring

USER spring:spring

# Copie os artefatos construídos da etapa anterior
COPY --from=builder /app/target/dependencies/ ./dependencies/
COPY --from=builder /app/target/spring-boot-loader/ ./spring-boot-loader/
COPY --from=builder /app/target/snapshot-dependencies/ ./snapshot-dependencies/
COPY --from=builder /app/target/application/ ./

EXPOSE 8080

ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]

#FROM amazoncorretto:17-alpine-jdk
#
#ENV EXPOSE_PORT=8080
#
## Adicione dependências do sistema necessárias para o GraalVM
##RUN apk --no-cache add libstdc++ libgcc
#
#ARG DEPENDENCY=/app/target/dependency
#COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
#COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
#COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
#
#EXPOSE $EXPOSE_PORT
#
#ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -cp app:app/lib/* br.com.fsales.Wellsmart.WellsmartApplication ${0} ${@}"]
#ENTRYPOINT ["java","-cp","app:app/lib/*","br.com.fsales.Wellsmart.WellsmartApplication"]

# mkdir target/extracted
# java -Djarmode=layertools -jar target/*.jar extract --destination target/extracted
# docker build -t fosales/nex-stream:latest -t fosales/nex-stream:0.0.1-SNAPSHOT .
# docker  build -f ./nex-stream/Dockerfile -t fosales/nex-stream:latest ./nex-stream/