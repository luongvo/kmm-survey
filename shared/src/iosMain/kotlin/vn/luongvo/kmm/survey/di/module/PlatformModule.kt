package vn.luongvo.kmm.survey.di.module

import com.russhwolf.settings.*
import io.ktor.client.engine.darwin.*
import org.koin.core.module.Module
import org.koin.dsl.module
import platform.Foundation.NSBundle

@OptIn(ExperimentalSettingsImplementation::class)
actual fun platformModule(): Module = module {
    single { Darwin.create() }

    val serviceName = NSBundle.mainBundle().bundleIdentifier.orEmpty()
    single<Settings> {
        KeychainSettings(serviceName)
    }
}
