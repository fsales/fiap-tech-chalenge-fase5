plugins {

    id("java")
    id("org.springframework.boot") version "3.2.3"
    id("io.spring.dependency-management") version "1.1.4"
}

val springDocVersion = "2.3.0"
val jwtAuth0Version = "4.4.0"

springBoot {
    mainClass.set("br.com.wells.app.WellsUsuarioAppApllication")
}

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
