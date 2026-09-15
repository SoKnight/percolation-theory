plugins {
    alias(libs.plugins.kotlin.jvm)
    application
}

group = "me.soknight.university"
version = "1.0-SNAPSHOT"

application {
    mainClass = "MainKt"

    applicationDefaultJvmArgs = listOf(
        "-Dstdout.encoding=UTF-8",
        "-Dstderr.encoding=UTF-8",
    )
}

kotlin {
    jvmToolchain(25)
}

repositories {
    mavenCentral()
}

tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}
