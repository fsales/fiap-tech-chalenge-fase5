# Makefile.maven contains shared tasks for building Java applications.
# This file is included in Makefiles containing Java sources to be built.

# Project Name
PROJECT_NAME = wells-core

# Project Directory
PROJECT_DIR = .

# Directory where pom.xml is located
POM_DIR = $(PROJECT_DIR)/$(PROJECT_NAME)

# Maven Arguments
MVN_ARGS =

# List of all Maven targets
MAVEN_TARGETS = java_build java_install

.PHONY: $(MAVEN_TARGETS) all clean

# Check for dependencies
CHECK_MAVEN := $(shell command -v mvn 2> /dev/null)

ifeq ($(CHECK_MAVEN),)
$(error 'mvn' not found. Please install Maven before running this Makefile.)
endif

# Default target to run all targets
all: $(MAVEN_TARGETS)
	@echo "All targets executed successfully."

# Target to build the Java project
java_build:
	@echo "Building, verifying, and packaging the project..."
	cd $(POM_DIR) && ./mvnw $(MVN_ARGS) package

# Target to install Maven artifacts
java_install: java_build
	@echo "Installing JAR files..."
	cd $(POM_DIR) && ./mvnw $(MVN_ARGS) install

# Target to clean build artifacts
clean:
	@echo "Cleaning up build artifacts..."
	cd $(POM_DIR) && ./mvnw clean