package vn.luongvo.kmm.survey.di.module

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import vn.luongvo.kmm.survey.data.repository.*
import vn.luongvo.kmm.survey.domain.repository.*

val repositoryModule = module {
    singleOf(::AuthRepositoryImpl) bind AuthRepository::class
    singleOf(::UserRepositoryImpl) bind UserRepository::class
    singleOf(::SurveyRepositoryImpl) bind SurveyRepository::class
}
