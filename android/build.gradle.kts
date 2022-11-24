plugins {
    id(Plugins.ANDROID_APPLICATION)
    kotlin(Plugins.ANDROID)
}

android {
    namespace = "vn.luongvo.kmm.survey.android"
    compileSdk = Versions.ANDROID_COMPILE_SDK_VERSION

    defaultConfig {
        applicationId = "vn.luongvo.kmm.survey.android"
        minSdk = Versions.ANDROID_MIN_SDK_VERSION
        targetSdk = Versions.ANDROID_TARGET_SDK_VERSION
        versionCode = Versions.ANDROID_VERSION_CODE
        versionName = Versions.ANDROID_VERSION_NAME
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.COMPOSE
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(Modules.SHARED))

    with(Deps.AndroidX) {
        implementation(ACTIVITY_COMPOSE)
    }

    with(Deps.Compose) {
        implementation(UI)
        implementation(UI_GRAPHICS)
        implementation(MATERIAL)
        implementation(NAVIGATION)
        implementation(UI_TOOLING)
    }

    with(Deps.Log) {
        implementation(TIMBER)
    }

    with(Deps.Test) {
        implementation(JUNIT)
        implementation(COROUTINES)
    }
}
