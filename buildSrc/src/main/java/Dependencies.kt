object Versions {
    const val ANDROID_COMPILE_SDK_VERSION = 33
    const val ANDROID_MIN_SDK_VERSION = 24
    const val ANDROID_TARGET_SDK_VERSION = 33

    const val ANDROID_VERSION_CODE = 1
    const val ANDROID_VERSION_NAME = "0.1.0"

    const val ANDROIDX_ACTIVITY_COMPOSE = "1.5.1"

    const val COMPOSE = "1.3.0"
    const val COMPOSE_NAVIGATION = "2.5.2"

    const val DETEKT = "1.21.0"

    const val FIREBASE_BOM = "31.1.0"

    const val GOOGLE_SERVICES = "4.3.13"
    const val GRADLE = "7.0.4"

    const val JUNIT = "4.13.2"

    const val KOTLIN = "1.7.10"
    const val KOTLIN_COROUTINES = "1.6.4"
    const val KOVER = "0.6.1"

    const val TIMBER = "4.7.1"
}

object Dependencies {
    object Gradle {
        const val KOTLIN_GRADLE_PLUGIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}"
        const val GRADLE = "com.android.tools.build:gradle:${Versions.GRADLE}"
    }

    object AndroidX {
        const val ACTIVITY_COMPOSE = "androidx.activity:activity-compose:${Versions.ANDROIDX_ACTIVITY_COMPOSE}"
    }

    object Compose {
        const val UI = "androidx.compose.ui:ui:${Versions.COMPOSE}"
        const val UI_GRAPHICS = "androidx.compose.ui:ui-graphics:${Versions.COMPOSE}"
        const val UI_TOOLING = "androidx.compose.ui:ui-tooling:${Versions.COMPOSE}"
        const val MATERIAL = "androidx.compose.material:material:${Versions.COMPOSE}"
        const val NAVIGATION = "androidx.navigation:navigation-compose:${Versions.COMPOSE_NAVIGATION}"
    }

    object Firebase {
        const val GOOGLE_SERVICES = "com.google.gms:google-services:${Versions.GOOGLE_SERVICES}"
        const val FIREBASE_BOM = "com.google.firebase:firebase-bom:${Versions.FIREBASE_BOM}"
        const val FIREBASE_ANALYTICS = "com.google.firebase:firebase-analytics-ktx"
    }

    object Log {
        const val TIMBER = "com.jakewharton.timber:timber:${Versions.TIMBER}"
    }

    object Test {
        const val JUNIT = "junit:junit:${Versions.JUNIT}"
        const val COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.KOTLIN_COROUTINES}"
    }
}
