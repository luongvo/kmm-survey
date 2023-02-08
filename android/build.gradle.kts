plugins {
    id(Plugins.ANDROID_APPLICATION)
    kotlin(Plugins.ANDROID)
    id(Plugins.GOOGLE_SERVICES)
    id(Plugins.KOVER)
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
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.COMPOSE_COMPILER
    }

    compileOptions {
        targetCompatibility = JavaVersion.VERSION_11
        sourceCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = Versions.JVM_TARGET
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1,*.md}"
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

    testOptions {
        unitTests {
            // Robolectric resource processing/loading https://github.com/robolectric/robolectric/pull/4736
            isIncludeAndroidResources = true
        }
        unitTests.all {
            if (it.name != "testStagingDebugUnitTest") {
                it.extensions.configure(kotlinx.kover.api.KoverTaskExtension::class) {
                    isDisabled.set(true)
                }
            }
        }
        animationsDisabled = true
        // https://github.com/mockk/mockk/issues/297#issuecomment-901924678
        packagingOptions {
            jniLibs {
                useLegacyPackaging = true
            }
        }
    }
}

dependencies {
    implementation(project(Modules.SHARED))

    with(Dependencies.AndroidX) {
        implementation(ACTIVITY_COMPOSE)
        implementation(LIFECYCLE_RUNTIME_COMPOSE)
    }
    with(Dependencies.Compose) {
        implementation(UI)
        implementation(UI_GRAPHICS)
        implementation(MATERIAL)
        implementation(NAVIGATION)
        implementation(UI_TOOLING)
        implementation(COIL_COMPOSE)
        implementation(ACCOMPANIST_PAGER)
        implementation(ACCOMPANIST_PAGER_INDICATORS)
        implementation(ACCOMPANIST_PLACEHOLDER)

        implementation(NUMBERPICKER)
    }
    with(Dependencies.Firebase) {
        implementation(platform(FIREBASE_BOM))
        implementation(FIREBASE_ANALYTICS)
    }
    with(Dependencies.Koin) {
        implementation(ANDROID)
        implementation(COMPOSE)
    }
    with(Dependencies.Log) {
        implementation(TIMBER)
    }

    with(Dependencies.Test) {
        testImplementation(COROUTINES)
        testImplementation(JUNIT)
        testImplementation(KOTEST_ASSERTIONS)
        testImplementation(MOCKK)
        testImplementation(TURBINE)

        testImplementation(COMPOSE_UI_TEST_JUNIT)
        debugImplementation(COMPOSE_UI_TEST_MANIFEST)
        testImplementation(MOCKK_ANDROID)
        testImplementation(ROBOLECTRIC)
    }
    // For test json resources parsing
    with(Dependencies.Ktor) {
        testImplementation(CORE)
        testImplementation(JSON)
        testImplementation(JSON_API)
    }
}
