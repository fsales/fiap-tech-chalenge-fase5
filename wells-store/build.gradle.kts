plugins {
    id("java")
    id("org.springframework.boot") version "3.2.2"
    id("io.spring.dependency-management") version "1.1.4"
}

allprojects{
    apply( plugin = "java")

    group = "br.com.fsales"
    version = "1.0-SNAPSHOT"

    java{
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    configurations {
        compileOnly {
            extendsFrom(configurations.annotationProcessor.get())
        }
    }


    repositories{
        mavenLocal()
        mavenCentral()
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

}

subprojects{

    dependencies {

        // test
        testImplementation(platform("org.junit:junit-bom:5.10.2"))
        testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
    }
}



