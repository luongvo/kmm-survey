package vn.luongvo.kmm.survey.android.test

import vn.luongvo.kmm.survey.domain.model.*

object Fake {

    val token = Token(
        tokenType = "tokenType",
        accessToken = "accessToken",
        refreshToken = "refreshToken"
    )

    val user = User(
        email = "email",
        name = "name",
        avatarUrl = "avatarUrl"
    )

    val surveys = emptyList<Survey>()
}
