plugins {
    id "java"
    id "org.springframework.boot" version "3.0.1"
    id "io.spring.dependency-management" version "1.1.0"
}

<#-- these args will be injected by class GradleTemplateInjectCore -->
group = "${group}"
version = "${version}"
sourceCompatibility = "17"

repositories {
    mavenCentral()
}

dependencies {
    implementation "org.springframework.boot:spring-boot-starter-web"
    implementation "org.springframework.boot:spring-boot-starter-data-jpa"
    runtimeOnly "com.mysql:mysql-connector-j"
    compileOnly "org.projectlombok:lombok"
    annotationProcessor "org.projectlombok:lombok"
}
