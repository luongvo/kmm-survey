package vn.luongvo.kmm.survey.di.module

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import vn.luongvo.kmm.survey.data.local.datasource.TokenLocalDataSource
import vn.luongvo.kmm.survey.data.local.datasource.TokenLocalDataSourceImpl

val localModule = module {
    singleOf(::TokenLocalDataSourceImpl) { bind<TokenLocalDataSource>() }
}
