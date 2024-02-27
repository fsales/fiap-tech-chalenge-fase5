plugins {
    id("java")
    id("org.springframework.boot") version "3.2.3"
    id("io.spring.dependency-management") version "1.1.4"
    id("org.sonarqube") version "4.4.1.3373"
}

tasks.named("bootJar"){
    enabled = false
}


allprojects {
    apply(plugin = "java")

    group = "br.com.fsales"
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
        testImplementation(platform("org.junit:junit-bom:5.10.2"))
        testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
    }

}


