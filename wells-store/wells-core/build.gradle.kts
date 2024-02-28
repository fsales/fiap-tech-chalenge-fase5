plugins {
    id("java")
    id("org.sonarqube") version "4.4.1.3373"
}

val springSecurityCriptoVersion = "6.1.5"
val commonsLoggingVersion = "1.3.0"
val assertjVersion = "3.25.3"
val lombokVersion = "1.18.30"

dependencies {

    implementation("org.springframework.security:spring-security-crypto:$springSecurityCriptoVersion")
    implementation("commons-logging:commons-logging:$commonsLoggingVersion")

    // lombok
    compileOnly("org.projectlombok:lombok:1.18.30")
   // implementation("org.projectlombok:lombok:$lombokVersion")


    testImplementation("org.assertj:assertj-core:$assertjVersion")
}