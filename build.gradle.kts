plugins {
    kotlin("jvm") version "2.4.10"
}

group = "me.soknight.university"
version = "1.0-SNAPSHOT"

kotlin {
    jvmToolchain(25)
}

repositories {
    mavenCentral()
}
