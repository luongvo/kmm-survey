package vn.luongvo.kmm.survey.di.module

import com.russhwolf.settings.KeychainSettings
import com.russhwolf.settings.Settings
import io.ktor.client.engine.darwin.*
import org.koin.core.module.Module
import org.koin.dsl.module
import vn.luongvo.kmm.survey.BuildConfig

private const val SERVICE_NAME = BuildConfig.LIBRARY_PACKAGE_NAME

actual fun platformModule(): Module = module {
    single { Darwin.create() }

    single<Settings> {
        KeychainSettings(
            BuildConfig.LIBRARY_PACKAGE_NAME
        )
    }
}
