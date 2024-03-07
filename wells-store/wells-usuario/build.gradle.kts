import org.springframework.boot.gradle.tasks.bundling.BootBuildImage
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot") version "3.2.3"
    id("io.spring.dependency-management") version "1.1.4"
}

val springDocVersion = "2.3.0"
val jwtAuth0Version = "4.4.0"
val mainClassWellsUsuario = "br.com.wells.app.WellsUsuarioAppApllication"

dependencies {
    // Wells
    implementation(project(":wells-core"))

    // JWT
    implementation("com.auth0:java-jwt:$jwtAuth0Version")

    // Spring
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.liquibase:liquibase-core")
    implementation("org.springframework.boot:spring-boot-starter-security")
    developmentOnly("org.springframework.boot:spring-boot-devtools")

    

    // Swagger
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:$springDocVersion")

    // Lombok
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    // Postgres
    runtimeOnly("org.postgresql:postgresql")

    // Test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
}

//tasks.named("bootJar") {
//    enabled = true
//
//}

tasks.named<BootJar>("bootJar") {
    enabled = true
    archiveFileName.set("wells-usuario.jar")
    layered {
        enabled.set(true)
        application {
            intoLayer("spring-boot-loader") {
                include("org/springframework/boot/loader/**")
            }
            intoLayer("application")
        }
        dependencies {
            intoLayer("application") {
                includeProjectDependencies()
            }
            intoLayer("snapshot-dependencies") {
                include("*:*:*SNAPSHOT")
            }
            intoLayer("dependencies")
        }
        layerOrder.set(listOf("dependencies", "spring-boot-loader", "snapshot-dependencies", "application"))
    }
    mainClass.set(mainClassWellsUsuario)
    manifest {
        attributes("Start-Class" to mainClassWellsUsuario)
    }
}

springBoot {
    mainClass.set(mainClassWellsUsuario)

    buildInfo {
		properties {
			artifact.set("wells-usuario")
			version.set("1.0")
			group.set("br.com.wells")
			name.set("Modulo de Gerenciamento de usuario")
		}
	}
}

tasks.named<BootBuildImage>("bootBuildImage") {
    //builder.set("paketobuildpacks/builder-jammy-base:latest")
    imageName = "${rootProject.name}/${project.name}"
    createdDate = "2024-07-03T10:10:10Z"
    environment = mapOf("BP_JVM_VERSION" to "17.*")

}
