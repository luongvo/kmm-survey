package vn.luongvo.kmm.survey.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import vn.luongvo.kmm.survey.di.module.*

fun initKoin(appDeclaration: KoinAppDeclaration = {}): KoinApplication {
    val dataModules = listOf(localModule, remoteModule, repositoryModule)
    val domainModules = listOf(useCaseModule)
    return startKoin {
        appDeclaration()
        modules(
            dataModules + domainModules + platformModule()
        )
    }
}
