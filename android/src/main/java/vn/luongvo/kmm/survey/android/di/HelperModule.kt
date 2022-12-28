package vn.luongvo.kmm.survey.android.di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import vn.luongvo.kmm.survey.android.util.DateFormatter
import vn.luongvo.kmm.survey.android.util.DateFormatterImpl

val helperModule = module {
    singleOf(::DateFormatterImpl) { bind<DateFormatter>() }
}
