# Project Directory
PROJECT_DIR = .

# Project Name
PROJECT_NAME = wells-store

# Directory where pom.xml is located
DIR = $(PROJECT_DIR)/$(PROJECT_NAME)


##############MAVEN######################
# Maven Arguments
MVN_ARGS =

# List of all Maven targets
MAVEN_TARGETS = java_install

# Check for dependencies
CHECK_MAVEN := $(shell command -v mvn 2> /dev/null)

ifeq ($(CHECK_MAVEN),)
$(error 'mvn' not found. Please install Maven before running this Makefile.)
endif

# Default target to run all targets
all: $(MAVEN_TARGETS)
	@echo "All targets executed successfully."

# Target to build the Java project
java_package: clean
	@echo "Building, verifying, and packaging the project..."
	cd $(DIR) && ./mvnw $(MVN_ARGS) package -DskipTests=true -U

# Target to install Maven artifacts
java_install: clean
	@echo "Installing JAR files..."
	cd $(DIR) && ./mvnw $(MVN_ARGS) install -U


# Target to clean build artifacts
clean:
	@echo "Cleaning up build artifacts..."
	cd $(DIR) && ./mvnw clean

##############DOCKER######################
# Docker Version
DOCKER_VERSION_LABEL_IMAGE = 1.0-SNAPSHOT

# DOCKER NAMESPACE
DOCKER_NAMESPACE = wells-store

# DOCKER ALL TARGETS
DOCKER_TARGETS = docker_build_quarto docker_build_servico_opcionais docker_build_clientes docker_build_reservas

docker_build_image_usuario: java_package
	@echo "Building Docker image for carrinho..."
	@NAME_APP=wells-usuario && \
	docker build \
		-f $(DIR)/$$NAME_APP/Dockerfile \
		--build-arg VERSION=$(DOCKER_VERSION_LABEL_IMAGE) \
		--build-arg JAR_FILE=$(DIR)/$$NAME_APP/target/$$NAME_APP-$(DOCKER_VERSION_LABEL_IMAGE).jar \
		-t $(DOCKER_NAMESPACE)/$(PROJECT_NAME)-$$NAME_APP:$(DOCKER_VERSION_LABEL_IMAGE) \
		-t $(DOCKER_NAMESPACE)/$(PROJECT_NAME)-$$NAME_APP:latest .

docker_build_image_pagamento: java_package
	@echo "Building Docker image for carrinho..."
	@NAME_APP=wells-pagamento && \
	docker build \
		-f $(DIR)/$$NAME_APP/Dockerfile \
		--build-arg VERSION=$(DOCKER_VERSION_LABEL_IMAGE) \
		--build-arg JAR_FILE=$(DIR)/$$NAME_APP/target/$$NAME_APP-$(DOCKER_VERSION_LABEL_IMAGE).jar \
		-t $(DOCKER_NAMESPACE)/$(PROJECT_NAME)-$$NAME_APP:$(DOCKER_VERSION_LABEL_IMAGE) \
		-t $(DOCKER_NAMESPACE)/$(PROJECT_NAME)-$$NAME_APP:latest .

docker_build_image_gateway: java_package
	@echo "Building Docker image for carrinho..."
	@NAME_APP=wells-gateway && \
	docker build \
		-f $(DIR)/$$NAME_APP/Dockerfile \
		--build-arg VERSION=$(DOCKER_VERSION_LABEL_IMAGE) \
		--build-arg JAR_FILE=$(DIR)/$$NAME_APP/target/$$NAME_APP-$(DOCKER_VERSION_LABEL_IMAGE).jar \
		-t $(DOCKER_NAMESPACE)/$(PROJECT_NAME)-$$NAME_APP:$(DOCKER_VERSION_LABEL_IMAGE) \
		-t $(DOCKER_NAMESPACE)/$(PROJECT_NAME)-$$NAME_APP:latest .

docker_build_image_produto: java_package
	@echo "Building Docker image for carrinho..."
	@NAME_APP=wells-produto && \
	docker build \
		-f $(DIR)/$$NAME_APP/Dockerfile \
		--build-arg VERSION=$(DOCKER_VERSION_LABEL_IMAGE) \
		--build-arg JAR_FILE=$(DIR)/$$NAME_APP/target/$$NAME_APP-$(DOCKER_VERSION_LABEL_IMAGE).jar \
		-t $(DOCKER_NAMESPACE)/$(PROJECT_NAME)-$$NAME_APP:$(DOCKER_VERSION_LABEL_IMAGE) \
		-t $(DOCKER_NAMESPACE)/$(PROJECT_NAME)-$$NAME_APP:latest .

docker_build_image_carrinho: java_package
	@echo "Building Docker image for carrinho..."
	@NAME_APP=wells-carrinho && \
	docker build \
		-f $(DIR)/$$NAME_APP/Dockerfile \
		--build-arg VERSION=$(DOCKER_VERSION_LABEL_IMAGE) \
		--build-arg JAR_FILE=$(DIR)/$$NAME_APP/target/$$NAME_APP-$(DOCKER_VERSION_LABEL_IMAGE).jar \
		-t $(DOCKER_NAMESPACE)/$(PROJECT_NAME)-$$NAME_APP:$(DOCKER_VERSION_LABEL_IMAGE) \
		-t $(DOCKER_NAMESPACE)/$(PROJECT_NAME)-$$NAME_APP:latest .
