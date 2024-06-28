plugins {
    java
    `maven-publish`
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("xyz.jpenilla.run-paper") version "2.3.0"
}

group = "dev.grafjojo"
version = "1.0"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21-R0.1-SNAPSHOT")
    implementation("org.postgresql:postgresql:42.7.3")
    implementation("com.zaxxer:HikariCP:5.1.0")
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
}

tasks {
    runServer {
        minecraftVersion("1.21")
    }

    withType<JavaCompile> {
        options.encoding = "UTF-8"
    }

    assemble {
        dependsOn("shadowJar")
    }
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}