package vn.luongvo.kmm.survey.di.module

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import vn.luongvo.kmm.survey.domain.usecase.*

val useCaseModule = module {
    singleOf(::LogInUseCaseImpl) bind LogInUseCase::class
    singleOf(::IsLoggedInUseCaseImpl) bind IsLoggedInUseCase::class
    singleOf(::GetUserProfileUseCaseImpl) bind GetUserProfileUseCase::class
    singleOf(::RefreshTokenUseCaseImpl) bind RefreshTokenUseCase::class
    singleOf(::GetSurveysUseCaseImpl) bind GetSurveysUseCase::class
    singleOf(::GetCachedSurveysUseCaseImpl) bind GetCachedSurveysUseCase::class
    singleOf(::GetSurveyDetailUseCaseImpl) bind GetSurveyDetailUseCase::class
    singleOf(::LogOutUseCaseImpl) bind LogOutUseCase::class
    singleOf(::SubmitSurveyUseCaseImpl) bind SubmitSurveyUseCase::class
}
