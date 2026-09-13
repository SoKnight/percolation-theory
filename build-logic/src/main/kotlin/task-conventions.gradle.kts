// Общая сборка для всех заданий
// Kotlin под Java 25, запуск через application, тесты на JUnit 5

plugins {
    id("org.jetbrains.kotlin.jvm")
    application
}

application {
    // точка входа каждого задания:
    // функция main в файле Main.kt
    mainClass = "MainKt"
}

kotlin {
    jvmToolchain(25)
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks {
    named<JavaExec>("run") {
        // без этого на Windows вывод идёт в системной кодировке, и кириллица превращается в «?»
        jvmArgs("-Dstdout.encoding=UTF-8", "-Dstderr.encoding=UTF-8")

        // по умолчанию Gradle не передаёт программе ввод с консоли, и читать данные ей неоткуда
        standardInput = System.`in`
    }

    assembleDist {
        enabled = false
    }

    distTar {
        enabled = false
    }

    distZip {
        enabled = false
    }

    startScripts {
        enabled = false
    }

    test {
        useJUnitPlatform()
    }
}
