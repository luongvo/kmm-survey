object Flavors {
    const val PRODUCTION = "production"
    const val STAGING = "staging"
    const val DIMENSION_VERSION = "version"
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

    const val BUILD_KONFIG = "com.codingfeline.buildkonfig"

    const val COCOAPODS = "native.cocoapods"

    const val DETEKT = "io.gitlab.arturbosch.detekt"

    const val GOOGLE_SERVICES = "com.google.gms.google-services"

    const val KOTLIN_SERIALIZATION = "plugin.serialization"
    const val KOTLINX_RESOURCES = "com.goncalossilva.resources"
    const val KOTLINX_SERIALIZATION = "kotlinx-serialization"
    const val KOVER = "kover"
    const val KOVER_PACKAGE = "org.jetbrains.kotlinx.kover"
    const val KSP = "com.google.devtools.ksp"

    const val MULTIPLATFORM = "multiplatform"
}

object XcodeConfiguration {
    const val STAGING_DEBUG = "Staging Debug"
    const val PRODUCTION_DEBUG ="Production Debug"
    const val STAGING_RELEASE = "Staging Release"
    const val PRODUCTION_RELEASE = "Production Release"
}
