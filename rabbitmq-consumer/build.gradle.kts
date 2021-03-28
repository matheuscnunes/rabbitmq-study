import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.4.1"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    kotlin("jvm") version "1.4.21"
    kotlin("plugin.spring") version "1.4.21"
    kotlin("kapt") version "1.4.21"
    id("com.google.cloud.tools.jib") version "2.7.1"
}

group = "matheus.nunes.study"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenLocal()
    mavenCentral()
    maven { url = uri("https://repo.spring.io/milestone") }
}

extra["springCloudVersion"] = "2020.0.0"
extra["projectLombokVersion"] = "1.18.18"

dependencies {
    // Spring
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-amqp")
    implementation("org.springframework.cloud:spring-cloud-starter-sleuth")
    implementation("org.springframework.boot:spring-boot-starter-json")

    // Kotlin
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // Tests
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.amqp:spring-rabbit-test")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

jib {
    from.image = "openjdk:11.0.9-jre-slim"

    container.creationTime = "USE_CURRENT_TIMESTAMP"
    container.jvmFlags = listOf(
            "-XX:+AlwaysActAsServerClassMachine",
            "-XX:TieredStopAtLevel=1",
            "-noverify",
            "-XX:+UseG1GC",
            "-XX:MinRAMPercentage=50.0",
            "-XX:MaxRAMPercentage=80.0"
    )
}
