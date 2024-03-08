# Makefile.maven contains the shared tasks for building Java applications. This file is
# included into the Makefile files which contain some Java sources which should be build

# Nome do projeto
PROJECT_NAME = wells-usuario

# Diretório onde está o projeto Java
PROJECT_DIR = .

# Diretório onde está o pom.xml
POM_DIR = $(PROJECT_DIR)/$(PROJECT_NAME)

# Argumentos do Maven
MVN_ARGS =

# Docker Version
DOCKER_VERSION_LABEL_IMAGE = 1.0

# DOCKER JAR_FILE
DOCKER_JAR_FILE = ./wells-usuario/target/wells-usuario-1.0-SNAPSHOT.jar

# DOCKER NAMESPACE
DOCKER_NAMESPACE = wells-store

# Lista de todas as metas Maven
MAVEN_TARGETS = java_build java_install docker_build docker_compose_up_wells_usuario

.PHONY: $(MAVEN_TARGETS) all clean

# Meta para executar todas as metas
all: $(MAVEN_TARGETS)
	@echo "Todas as metas foram executadas com sucesso."

# Meta para construir o projeto Java
java_build:
	@echo "Building, verifying, and packaging the project..."
	cd $(POM_DIR) && ./mvnw $(MVN_ARGS) package

# Meta para instalar artefatos Maven
java_install:
	@echo "Installing JAR files..."
	cd $(POM_DIR) && ./mvnw $(MVN_ARGS) install

# Meta para construir a imagem Docker
docker_build: java_build
	@echo "Running build of the wells-user docker image..."
	docker build \
		-f $(POM_DIR)/Dockerfile \
		--build-arg VERSION=$(DOCKER_VERSION_LABEL_IMAGE) \
		--build-arg JAR_FILE=$(DOCKER_JAR_FILE) \
		-t $(DOCKER_NAMESPACE)/$(PROJECT_NAME):$(DOCKER_VERSION_LABEL_IMAGE) \
		-t $(DOCKER_NAMESPACE)/$(PROJECT_NAME):latest .

docker_compose_up_wells_usuario: docker_build
	@echo "Running docker-compose up..."
	docker-compose -f ../docker/docker-compose-wells-usuario.yaml -p wells-usuario-api up -d --force-recreate  --remove-orphans

docker_compose_down_wells_usuario:
	@echo "Running docker-compose down..."
	docker-compose -f ../docker/docker-compose-wells-usuario.yaml down --remove-orphans

docker_compose_down_wells_usuario_remove_volumes:
	@echo "Running docker-compose down with volumes..."
	docker-compose -f ../docker/docker-compose-wells-usuario.yaml down --remove-orphans --volumes --rmi all

# Meta para limpar artefatos de construção
clean:
	@echo "Cleaning up build artifacts..."
	cd $(POM_DIR) && ./mvnw clean
	docker rmi -f $(DOCKER_NAMESPACE)/$(PROJECT_NAME):$(DOCKER_VERSION_LABEL_IMAGE)
	docker rmi -f $(DOCKER_NAMESPACE)/$(PROJECT_NAME):latest


