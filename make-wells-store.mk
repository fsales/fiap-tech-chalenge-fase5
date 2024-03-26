# Project Directory
PROJECT_DIR = .

# Project Name
PROJECT_NAME = wells-store

# Directory where pom.xml is located
DIR = $(PROJECT_DIR)/$(PROJECT_NAME)

##############MAVEN######################
# Maven Arguments
MVN_ARGS =

# Maven Wrapper
MVNW = ./mvnw

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
	cd $(DIR) && $(MVNW) $(MVN_ARGS) package -DskipTests=true -U

# Target to install Maven artifacts
java_install: clean
	@echo "Installing JAR files..."
	cd $(DIR) && $(MVNW) $(MVN_ARGS) install -U

# Target to clean build artifacts
clean:
	@echo "Cleaning up build artifacts..."
	cd $(DIR) && $(MVNW) clean

##############DOCKER######################
# Docker Version
DOCKER_VERSION_LABEL_IMAGE = 1.0-SNAPSHOT

# DOCKER NAMESPACE
DOCKER_NAMESPACE = wells-store

# Docker Build Command
DOCKER_BUILD_CMD = docker build \
	-f $(DIR)/$$NAME_APP/Dockerfile \
	--build-arg VERSION=$(DOCKER_VERSION_LABEL_IMAGE) \
	--build-arg JAR_FILE=$(DIR)/$$NAME_APP/target/$$NAME_APP-$(DOCKER_VERSION_LABEL_IMAGE).jar \
	-t $(DOCKER_NAMESPACE)/$(PROJECT_NAME)-$$NAME_APP:$(DOCKER_VERSION_LABEL_IMAGE) \
	-t $(DOCKER_NAMESPACE)/$(PROJECT_NAME)-$$NAME_APP:latest .

##############DOCKER BUILD IMAGES######################
DOCKER_TARGETS = docker_build_image_usuario docker_build_image_pagamento docker_build_image_gateway docker_build_image_produto docker_build_image_carrinho

docker_build_image_usuario:
	@echo "Building Docker image for carrinho..."
	@NAME_APP=wells-usuario && \
	$(DOCKER_BUILD_CMD)

docker_build_image_pagamento:
	@echo "Building Docker image for carrinho..."
	@NAME_APP=wells-pagamento && \
	$(DOCKER_BUILD_CMD)

docker_build_image_gateway:
	@echo "Building Docker image for carrinho..."
	@NAME_APP=wells-gateway && \
	$(DOCKER_BUILD_CMD)

docker_build_image_produto:
	@echo "Building Docker image for carrinho..."
	@NAME_APP=wells-produto && \
	$(DOCKER_BUILD_CMD)

docker_build_image_carrinho:
	@echo "Building Docker image for carrinho..."
	@NAME_APP=wells-carrinho && \
	$(DOCKER_BUILD_CMD)

docker_build_all: java_package $(DOCKER_TARGETS)
	@echo "Building all Docker images..."
