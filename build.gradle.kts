// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(Dependencies.Gradle.GRADLE)
        classpath(Dependencies.Gradle.KOTLIN_GRADLE_PLUGIN)
        classpath(Dependencies.Firebase.GOOGLE_SERVICES)
    }
}

plugins {
    id(Plugins.DETEKT).version(Versions.DETEKT)
    id(Plugins.KOVER_PACKAGE).version(Versions.KOVER)
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

detekt {
    toolVersion = Versions.DETEKT

    source = files(
        "android/src",
        "shared/src"
    )
    parallel = false
    config = files("detekt-config.yml")
    buildUponDefaultConfig = false
    disableDefaultRuleSets = false

    ignoreFailures = false

    ignoredBuildTypes = listOf(BuildTypes.RELEASE)
    ignoredFlavors = listOf(Flavors.PRODUCTION)
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    reports {
        html.required.set(true)
        xml.required.set(true)
    }
}

koverMerged {
    enable()

    val generatedFiles = emptySet<String>()

    val excludedPackages = emptySet<String>()

    val excludedFiles = generatedFiles + excludedPackages
    filters {
        classes {
            excludes += excludedFiles
        }
    }
}
