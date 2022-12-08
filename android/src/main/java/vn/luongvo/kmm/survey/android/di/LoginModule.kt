package vn.luongvo.kmm.survey.android.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import vn.luongvo.kmm.survey.android.ui.screens.login.LoginViewModel

val loginModule = module {
    viewModelOf(::LoginViewModel)
}
