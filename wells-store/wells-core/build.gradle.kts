plugins {
    id("java")
    id("org.sonarqube") version "4.4.1.3373"
}

dependencies {

    implementation("org.springframework.security:spring-security-crypto:6.1.5")
    implementation("commons-logging:commons-logging:1.3.0")

    testImplementation("org.assertj:assertj-core:3.25.3")
}

configurations{
    all {
        resolutionStrategy {
            force("commons-logging:commons-logging:1.3.0")
        }
    }
}