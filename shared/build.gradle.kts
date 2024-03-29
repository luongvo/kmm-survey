import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
import org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType

plugins {
    kotlin(Plugins.MULTIPLATFORM)
    kotlin(Plugins.COCOAPODS)
    kotlin(Plugins.KOTLIN_SERIALIZATION)
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.BUILD_KONFIG)
    id(Plugins.KOTLINX_SERIALIZATION)
    id(Plugins.KOVER)
    id(Plugins.KSP).version(Versions.KSP)
    id(Plugins.KOTLINX_RESOURCES).version(Versions.KOTLINX_RESOURCES)
    id(Plugins.REALM)
}

kotlin {
    android()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../ios/Podfile")
        framework {
            baseName = "shared"
        }
        xcodeConfigurationToNativeBuildType[XcodeConfiguration.STAGING_DEBUG] = NativeBuildType.DEBUG
        xcodeConfigurationToNativeBuildType[XcodeConfiguration.STAGING_RELEASE] = NativeBuildType.RELEASE
        xcodeConfigurationToNativeBuildType[XcodeConfiguration.PRODUCTION_DEBUG] = NativeBuildType.DEBUG
        xcodeConfigurationToNativeBuildType[XcodeConfiguration.PRODUCTION_RELEASE] = NativeBuildType.RELEASE
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Dependencies.Koin.CORE)
                with(Dependencies.Ktor) {
                    implementation(CORE)
                    implementation(SERIALIZATION)
                    implementation(LOGGING)
                    implementation(CIO)
                    implementation(CONTENT_NEGOTIATION)
                    implementation(JSON)
                    implementation(AUTH)
                    implementation(JSON_API)
                }
                implementation(Dependencies.Storage.SETTINGS)
                implementation(Dependencies.Storage.REALM_CORE)
                implementation(Dependencies.Log.NAPIER)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                with(Dependencies.Test) {
                    implementation(COROUTINES)
                    implementation(KOTEST_ASSERTIONS)
                    implementation(KOTLINX_RESOURCES)
                    implementation(MOCKATIVE)
                    implementation(TURBINE)
                }
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(Dependencies.Ktor.ANDROID)
                implementation(Dependencies.AndroidX.SECURITY_CRYPTO_KTX)
            }
        }
        val androidTest by getting

        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation(Dependencies.Ktor.IOS)
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

// https://github.com/mockative/mockative#installation-for-multiplatform-projects
dependencies {
    configurations
        .filter { it.name.startsWith("ksp") && it.name.contains("Test") }
        .forEach {
            add(it.name, Dependencies.Test.MOCKATIVE_PROCESSOR)
        }
}

// https://github.com/mockative/mockative#implicit-stubbing-of-functions-returning-unit
ksp {
    arg("mockative.stubsUnitByDefault", "true")
}

android {
    namespace = "vn.luongvo.kmm.survey"
    compileSdk = Versions.ANDROID_COMPILE_SDK_VERSION
    defaultConfig {
        minSdk = Versions.ANDROID_MIN_SDK_VERSION
        targetSdk = Versions.ANDROID_TARGET_SDK_VERSION
    }
}

val buildKonfigProperties = rootDir.loadGradleProperties("buildKonfig.properties")
buildkonfig {
    packageName = "vn.luongvo.kmm.survey"

    // Default for Flavors.STAGING
    defaultConfigs {
        buildConfigField(
            STRING,
            "CLIENT_ID",
            buildKonfigProperties.getProperty("STAGING_CLIENT_ID")
        )
        buildConfigField(
            STRING,
            "CLIENT_SECRET",
            buildKonfigProperties.getProperty("STAGING_CLIENT_SECRET")
        )
        buildConfigField(
            STRING,
            "BASE_URL",
            buildKonfigProperties.getProperty("STAGING_BASE_URL")
        )
    }

    defaultConfigs(Flavors.PRODUCTION) {
        buildConfigField(
            STRING,
            "CLIENT_ID",
            buildKonfigProperties.getProperty("PRODUCTION_CLIENT_ID")
        )
        buildConfigField(
            STRING,
            "CLIENT_SECRET",
            buildKonfigProperties.getProperty("PRODUCTION_CLIENT_SECRET")
        )
        buildConfigField(
            STRING,
            "BASE_URL",
            buildKonfigProperties.getProperty("PRODUCTION_BASE_URL")
        )
    }
}
