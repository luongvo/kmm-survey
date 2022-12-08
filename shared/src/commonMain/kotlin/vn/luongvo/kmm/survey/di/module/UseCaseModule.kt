package vn.luongvo.kmm.survey.di.module

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import vn.luongvo.kmm.survey.domain.usecase.LogInUseCase
import vn.luongvo.kmm.survey.domain.usecase.LogInUseCaseImpl

val useCaseModule = module {
    singleOf(::LogInUseCaseImpl) bind LogInUseCase::class
}
