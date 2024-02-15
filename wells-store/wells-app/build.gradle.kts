plugins {

    id("java")
    id("org.springframework.boot") version "3.2.2"
    id("io.spring.dependency-management") version "1.1.4"
}

springBoot{
    mainClass.set("br.com.fsales.wells.app.WellsAppApllication")
}


dependencies{
    implementation("org.springframework.boot:spring-boot-starter")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}