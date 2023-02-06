plugins {
    id "java"
    id "org.springframework.boot" version "3.0.1"
    id "io.spring.dependency-management" version "1.1.0"
}

group = "${group}"
version = "${version}"
sourceCompatibility = "17"

ext {
    mybatisPlusVersion = "3.5.3"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation "org.springframework.boot:spring-boot-starter-web"
    implementation "com.baomidou:mybatis-plus-boot-starter:${r'$mybatisPlusVersion'}"
    runtimeOnly "com.mysql:mysql-connector-j"
    compileOnly "org.projectlombok:lombok"
    annotationProcessor "org.projectlombok:lombok"
}
