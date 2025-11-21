plugins {
    id("java")
    id("com.gradleup.shadow") version "9.2.2"
    id("io.papermc.paperweight.userdev") version "2.0.0-beta.19"
    id("io.freefair.lombok") version "9.1.0"
    id("xyz.jpenilla.run-paper") version "2.3.1"
}

group = "net.lumamc.dhs"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://storehouse.okaeri.eu/repository/maven-public/")
}

dependencies {
    paperweight.paperDevBundle("1.21.10-R0.1-SNAPSHOT")
    implementation("eu.okaeri:okaeri-configs-yaml-bukkit:5.0.5") // storehouse.okaeri.eu
    implementation("eu.okaeri:okaeri-configs-serdes-bukkit:5.0.5")  // storehouse.okaeri.eu
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(21)
}

tasks {

    shadowJar {
        relocate("eu.okaeri", "net.lumamc.dhs.libs.okaeri")
        archiveClassifier = ""
    }

    jar {
        enabled = false
    }

    build {
        dependsOn(shadowJar)
    }

    runServer {
        minecraftVersion("1.21.10")
    }
}