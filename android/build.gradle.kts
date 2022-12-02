plugins {
    id(Plugins.ANDROID_APPLICATION)
    kotlin(Plugins.ANDROID)
    id(Plugins.GOOGLE_SERVICES)
}

val keystoreProperties = rootDir.loadGradleProperties("signing.properties")
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

    signingConfigs {
        create(BuildTypes.RELEASE) {
            // Remember to edit signing.properties to have the correct info for release build.
            storeFile = file("../config/release.keystore")
            storePassword = keystoreProperties.getProperty("KEYSTORE_PASSWORD") as String
            keyPassword = keystoreProperties.getProperty("KEY_PASSWORD") as String
            keyAlias = keystoreProperties.getProperty("KEY_ALIAS") as String
        }

        getByName(BuildTypes.DEBUG) {
            storeFile = file("../config/debug.keystore")
            storePassword = "oQ4mL1jY2uX7wD8q"
            keyAlias = "debug-key-alias"
            keyPassword = "oQ4mL1jY2uX7wD8q"
        }
    }

    buildTypes {
        getByName(BuildTypes.RELEASE) {
            isMinifyEnabled = true
            isDebuggable = false
            isShrinkResources = true
            signingConfig = signingConfigs[BuildTypes.RELEASE]
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }

        getByName(BuildTypes.DEBUG) {
            // For quickly testing build with proguard, enable this
            isMinifyEnabled = false
            signingConfig = signingConfigs[BuildTypes.DEBUG]
        }
    }

    flavorDimensions += Flavors.DIMENSION_VERSION
    productFlavors {
        create(Flavors.STAGING) {
            applicationIdSuffix = ".staging"
        }

        create(Flavors.PRODUCTION) {}
    }
}

dependencies {
    implementation(project(Modules.SHARED))

    with(Dependencies.AndroidX) {
        implementation(ACTIVITY_COMPOSE)
    }

    with(Dependencies.Compose) {
        implementation(UI)
        implementation(UI_GRAPHICS)
        implementation(MATERIAL)
        implementation(NAVIGATION)
        implementation(UI_TOOLING)
    }

    with(Dependencies.Firebase) {
        implementation(platform(FIREBASE_BOM))
        implementation(FIREBASE_ANALYTICS)
    }

    with(Dependencies.Log) {
        implementation(TIMBER)
    }

    with(Dependencies.Test) {
        implementation(JUNIT)
        implementation(COROUTINES)
    }
}
