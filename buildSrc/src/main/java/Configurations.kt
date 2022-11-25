object Flavors {
    const val PRODUCTION = "production"
    const val STAGING = "staging"
    const val DIMENSION = "stage"
}

object BuildTypes {
    const val DEBUG = "debug"
    const val RELEASE = "release"
}

object Modules {
    const val SHARED = ":shared"
}

object Plugins {
    const val ANDROID = "android"
    const val ANDROID_APPLICATION = "com.android.application"
    const val ANDROID_LIBRARY = "com.android.library"

    const val MULTIPLATFORM = "multiplatform"
    const val COCOAPODS = "native.cocoapods"

    const val KOTLIN_SERIALIZATION = "plugin.serialization"
    const val KOTLINX_SERIALIZATION = "kotlinx-serialization"
}
