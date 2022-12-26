package vn.luongvo.kmm.survey.android

import android.app.Application
import org.koin.android.ext.koin.androidContext
import timber.log.Timber
import vn.luongvo.kmm.survey.android.di.homeModule
import vn.luongvo.kmm.survey.android.di.loginModule
import vn.luongvo.kmm.survey.di.initKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(applicationContext)
            modules(loginModule)
            modules(homeModule)
        }
        setupLogging()
    }

    private fun setupLogging() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
