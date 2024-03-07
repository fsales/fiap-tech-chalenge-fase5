//plugins {
//    id("java")
//}

val springSecurityCriptoVersion = "6.1.5"
val commonsLoggingVersion = "1.3.0"
val commonsValidator = "1.8.0"
val assertjVersion = "3.25.3"
val lombokVersion = "1.18.30"

dependencies {

    implementation("org.springframework.security:spring-security-crypto:$springSecurityCriptoVersion")

    implementation("commons-logging:commons-logging:$commonsLoggingVersion")
    implementation("commons-validator:commons-validator:$commonsValidator")

    // lombok
    compileOnly("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")

    testImplementation("org.assertj:assertj-core:$assertjVersion")
}

tasks {
    // Configuração da tarefa 'jar' para criar um arquivo JAR convencional
    jar {
        enabled = true
    }
}