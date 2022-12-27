object Versions {
    const val ANDROID_COMPILE_SDK_VERSION = 33
    const val ANDROID_MIN_SDK_VERSION = 24
    const val ANDROID_TARGET_SDK_VERSION = 33

    const val ANDROID_VERSION_CODE = 1
    const val ANDROID_VERSION_NAME = "0.3.0"

    const val ANDROIDX_ACTIVITY_COMPOSE = "1.5.1"
    const val ANDROIDX_SECURITY_CRYPTO = "1.1.0-alpha04"

    const val BUILD_KONFIG = "0.13.3"

    const val COMPOSE_COMPILER = "1.3.2"
    const val COMPOSE_MATERIAL = "1.3.1"
    const val COMPOSE_NAVIGATION = "2.5.2"
    const val COMPOSE_UI = "1.3.2"

    const val DETEKT = "1.21.0"

    const val FIREBASE_BOM = "31.1.0"

    const val GOOGLE_SERVICES = "4.3.13"
    const val GRADLE = "7.0.4"

    const val JSON_API = "0.1.0"
    const val JUNIT = "4.13.2"
    const val JVM_TARGET = "1.8"

    const val KOIN = "3.2.2"
    const val KOIN_ANDROID = "3.3.0"
    const val KOTEST = "5.5.4"
    const val KOTLIN = "1.7.20"
    const val KOTLIN_COROUTINES = "1.6.4"
    const val KOVER = "0.6.1"
    const val KTOR = "2.1.1"
    const val KSP = "1.7.20-1.0.6"

    const val MOCKATIVE = "1.3.0"
    const val MOCKK = "1.13.3"

    const val NAPIER = "2.6.1"

    const val SETTINGS = "1.0.0-RC"

    const val TIMBER = "4.7.1"
    const val TURBINE = "0.12.1"
    const val ROBOLECTRIC = "4.9.1"
}

object Dependencies {
    object Gradle {
        const val BUILD_KONFIG = "com.codingfeline.buildkonfig:buildkonfig-gradle-plugin:${Versions.BUILD_KONFIG}"
        const val GRADLE = "com.android.tools.build:gradle:${Versions.GRADLE}"
    }

    object Kotlin {
        const val KOTLIN_GRADLE_PLUGIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}"
        const val KOTLIN_SERIALIZATION = "org.jetbrains.kotlin:kotlin-serialization:${Versions.KOTLIN}"
    }

    object AndroidX {
        const val ACTIVITY_COMPOSE = "androidx.activity:activity-compose:${Versions.ANDROIDX_ACTIVITY_COMPOSE}"
        const val SECURITY_CRYPTO_KTX = "androidx.security:security-crypto-ktx:${Versions.ANDROIDX_SECURITY_CRYPTO}"
    }

    object Compose {
        const val UI = "androidx.compose.ui:ui:${Versions.COMPOSE_UI}"
        const val UI_GRAPHICS = "androidx.compose.ui:ui-graphics:${Versions.COMPOSE_UI}"
        const val UI_TOOLING = "androidx.compose.ui:ui-tooling:${Versions.COMPOSE_UI}"
        const val MATERIAL = "androidx.compose.material:material:${Versions.COMPOSE_MATERIAL}"
        const val NAVIGATION = "androidx.navigation:navigation-compose:${Versions.COMPOSE_NAVIGATION}"
    }

    object Firebase {
        const val GOOGLE_SERVICES = "com.google.gms:google-services:${Versions.GOOGLE_SERVICES}"
        const val FIREBASE_BOM = "com.google.firebase:firebase-bom:${Versions.FIREBASE_BOM}"
        const val FIREBASE_ANALYTICS = "com.google.firebase:firebase-analytics-ktx"
    }

    object Koin {
        const val CORE = "io.insert-koin:koin-core:${Versions.KOIN}"
        const val ANDROID = "io.insert-koin:koin-android:${Versions.KOIN_ANDROID}"
        const val COMPOSE = "io.insert-koin:koin-androidx-compose:${Versions.KOIN_ANDROID}"
    }

    object Ktor {
        const val CORE = "io.ktor:ktor-client-core:${Versions.KTOR}"
        const val AUTH = "io.ktor:ktor-client-auth:${Versions.KTOR}"
        const val CIO = "io.ktor:ktor-client-cio:${Versions.KTOR}"
        const val CONTENT_NEGOTIATION = "io.ktor:ktor-client-content-negotiation:${Versions.KTOR}"
        const val JSON = "io.ktor:ktor-serialization-kotlinx-json:${Versions.KTOR}"
        const val LOGGING = "io.ktor:ktor-client-logging:${Versions.KTOR}"
        const val SERIALIZATION = "io.ktor:ktor-client-serialization:${Versions.KTOR}"
        const val ANDROID = "io.ktor:ktor-client-android:${Versions.KTOR}"
        const val IOS = "io.ktor:ktor-client-ios:${Versions.KTOR}"
        const val JSON_API = "co.nimblehq.jsonapi:core:${Versions.JSON_API}"
    }

    object Settings {
        const val SETTINGS = "com.russhwolf:multiplatform-settings:${Versions.SETTINGS}"
    }

    object Log {
        const val NAPIER = "io.github.aakira:napier:${Versions.NAPIER}"
        const val TIMBER = "com.jakewharton.timber:timber:${Versions.TIMBER}"
    }

    object Test {
        const val COMPOSE_UI_TEST_JUNIT = "androidx.compose.ui:ui-test-junit4:${Versions.COMPOSE_UI}"
        const val COMPOSE_UI_TEST_MANIFEST = "androidx.compose.ui:ui-test-manifest:${Versions.COMPOSE_UI}"
        const val COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.KOTLIN_COROUTINES}"

        const val JUNIT = "junit:junit:${Versions.JUNIT}"

        const val KOTEST_ASSERTIONS = "io.kotest:kotest-assertions-core:${Versions.KOTEST}"
        const val MOCKATIVE = "io.mockative:mockative:${Versions.MOCKATIVE}"
        const val MOCKATIVE_PROCESSOR = "io.mockative:mockative-processor:${Versions.MOCKATIVE}"
        const val MOCKK = "io.mockk:mockk:${Versions.MOCKK}"
        const val MOCKK_ANDROID = "io.mockk:mockk-android:${Versions.MOCKK}"

        const val ROBOLECTRIC = "org.robolectric:robolectric:${Versions.ROBOLECTRIC}"

        const val TURBINE = "app.cash.turbine:turbine:${Versions.TURBINE}"
    }
}
