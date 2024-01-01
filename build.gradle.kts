import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.2.1"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "1.9.21"
    kotlin("plugin.spring") version "1.9.21"
    kotlin("plugin.jpa") version "1.9.21"

    id("org.jlleitschuh.gradle.ktlint") version "10.2.0"
}

group = "com.everywaffle"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    // Spring Boot Starters for basic setup
    implementation("org.springframework.boot:spring-boot-starter-data-jpa") // Spring Boot starter for JPA
    implementation("org.springframework.boot:spring-boot-starter-security") // Spring Boot starter for security
    implementation("org.springframework.boot:spring-boot-starter-web") // Spring Boot starter for web applications

    // JSON Processing
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin") // Serialize/deserialize Kotlin classes to/from JSON

    // Kotlin Reflection
    implementation("org.jetbrains.kotlin:kotlin-reflect") // For Kotlin reflection capabilities

    // Database
    runtimeOnly("com.h2database:h2") // H2 in-memory database

    // Testing Dependencies
    testImplementation("org.springframework.boot:spring-boot-starter-test") // Spring Boot testing support
    testImplementation("org.springframework.security:spring-security-test") // Spring Security testing support

    // JWT Handling
    implementation("com.auth0:java-jwt:4.4.0") // For JWT creation and validation

    // JUnit Jupiter API & Mockito Kotlin
    testImplementation("org.mockito.kotlin:mockito-kotlin:4.1.0") // Mockito for Kotlin
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.2") // JUnit Jupiter engine
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}