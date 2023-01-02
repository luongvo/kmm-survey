package vn.luongvo.kmm.survey.android.ui.screens.home

import vn.luongvo.kmm.survey.domain.model.User

data class UserUiModel(
    val email: String,
    val name: String,
    val avatarUrl: String
)

fun User.toUiModel() = UserUiModel(
    email = email,
    name = name,
    avatarUrl = avatarUrl
)
