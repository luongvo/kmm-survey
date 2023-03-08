package vn.luongvo.kmm.survey.di.module

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import vn.luongvo.kmm.survey.data.local.datasource.*
import vn.luongvo.kmm.survey.data.local.realm.realm

val localModule = module {
    single { realm }
    singleOf(::TokenLocalDataSourceImpl) { bind<TokenLocalDataSource>() }
    singleOf(::SurveyLocalDataSourceImpl) { bind<SurveyLocalDataSource>() }
}
