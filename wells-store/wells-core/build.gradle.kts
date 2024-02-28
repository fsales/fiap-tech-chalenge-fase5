plugins {
    id("java")
    id("org.sonarqube") version "4.4.1.3373"
}

val springSecurityCriptoVersion = "6.1.5"
val commonsLoggingVersion = "1.3.0"
val assertjVersion = "3.25.3"

dependencies {

    implementation("org.springframework.security:spring-security-crypto:$springSecurityCriptoVersion")
    implementation("commons-logging:commons-logging:$commonsLoggingVersion")

    testImplementation("org.assertj:assertj-core:$assertjVersion")
}

configurations{
    all {
        resolutionStrategy {
            force("commons-logging:commons-logging:1.3.0")
        }
    }
}