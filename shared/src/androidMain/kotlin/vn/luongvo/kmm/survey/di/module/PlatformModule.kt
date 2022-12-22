package vn.luongvo.kmm.survey.di.module

import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings
import io.ktor.client.engine.android.*
import org.koin.core.module.Module
import org.koin.dsl.module
import vn.luongvo.kmm.survey.BuildConfig

private const val SHARED_PREFS_NAME = BuildConfig.LIBRARY_PACKAGE_NAME

actual fun platformModule(): Module = module {
    single { Android.create() }

    single<Settings> {
        SharedPreferencesSettings(
            EncryptedSharedPreferences.create(
                get(),
                SHARED_PREFS_NAME,
                MasterKey.Builder(get()).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build(),
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
        )
    }
}
