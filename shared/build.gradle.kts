import org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType

plugins {
    kotlin(Plugins.MULTIPLATFORM)
    kotlin(Plugins.COCOAPODS)
    id(Plugins.ANDROID_LIBRARY)
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
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting
        val androidTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
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

android {
    namespace = "vn.luongvo.kmm.survey"
    compileSdk = Versions.ANDROID_COMPILE_SDK_VERSION
    defaultConfig {
        minSdk = Versions.ANDROID_MIN_SDK_VERSION
        targetSdk = Versions.ANDROID_TARGET_SDK_VERSION
    }
}
