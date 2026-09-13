pluginManagement {
    // convention-плагины с общей логикой сборки заданий
    includeBuild("build-logic")
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "percolation-theory-monorepo"
