#!/bin/bash

# Inicializa o SDKMAN
source /root/.sdkman/bin/sdkman-init.sh

# Instala a última versão do Amazon Corretto 17 e define como padrão
sdk install java 17.0.0-amzn
sdk default java 17.0.0-amzn

# Obtém a versão do Java 11 que está instalada
java11_version=$(sdk list java | grep ' 11.' | awk '{print $NF}')

# Remove a versão do Java 11 que está instalada
if [ -n "$java11_version" ]; then
    sdk uninstall java $java11_version
fi