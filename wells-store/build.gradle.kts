plugins {
    id("java")
    id("org.springframework.boot") version "3.2.3"
    id("io.spring.dependency-management") version "1.1.4"
    id("org.sonarqube") version "4.4.1.3373"
}

// Defina uma variável para a versão de dependências
val junitVersion = "5.10.2"
val mockitoVersion = "5.7.0"

tasks.named("bootJar") {
    enabled = false
}


allprojects {
    apply(plugin = "java")

    group = "br.com.wells"
    version = "1.0"

    java {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    configurations {
        compileOnly {
            extendsFrom(configurations.annotationProcessor.get())
        }
    }


    repositories {
        mavenLocal()
        mavenCentral()
    }

    tasks.withType<Test> {
        useJUnitPlatform()
        // Configura a codificação de caracteres para os testes
        systemProperty("file.encoding", "UTF-8")
    }

    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
    }

    tasks.withType<JavaExec> {
        systemProperty("file.encoding", "UTF-8")
    }

    sonar {
        properties {
            property("sonar.projectKey", "fsales_fiap-tech-chalenge-fase5")
            property("sonar.organization", "fsales")
            property("sonar.host.url", "https://sonarcloud.io")
        }
    }
}

subprojects {

    dependencies {

        // test
        testImplementation(platform("org.junit:junit-bom:$junitVersion"))
        testImplementation("org.junit.jupiter:junit-jupiter:$junitVersion")
        testImplementation("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
        testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
        testImplementation("org.mockito:mockito-core:$mockitoVersion")
    }

}


