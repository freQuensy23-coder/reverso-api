plugins {
    kotlin("jvm") version "1.8.0"
    kotlin("plugin.serialization") version "1.8.10"
    `maven-publish`
    application
}

group = "org.example"
version = "1.2"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    implementation("org.junit.jupiter:junit-jupiter:5.8.1")
    testImplementation(kotlin("test"))
    implementation("com.squareup.retrofit2:retrofit:latest.release")
    implementation("com.squareup.retrofit2:converter-jackson:latest.release")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
    implementation("com.squareup.okhttp3:okhttp:4.7.2")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.+")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}

publishing {
    publications {
        create<MavenPublication>("reverseo") {
            pom {
                name.set("Reverso context API")
                description.set("Unofficial API wrapper for Reverso context")
                url.set("https://github.com/freQuensy23-coder/reverso-api/tree/main")
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("freQuensy23-coder")
                        name.set("Alexey Mametev")
                        email.set("mametevalex@gmail.com")
                    }
                }
                scm {
                    url.set("https://github.com/freQuensy23-coder/reverso-api/tree/main/")
                }
            }
        }
    }
}