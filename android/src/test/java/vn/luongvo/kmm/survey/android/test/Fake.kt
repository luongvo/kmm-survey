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

    val surveys = listOf(
        Survey(
            id = "1",
            title = "Scarlett Bangkok",
            description = "We'd love to hear from you!",
            coverImageUrl = "https://dhdbhh0jsld0o.cloudfront.net/m/1ea51560991bcb7d00d0_"
        ),
        Survey(
            id = "2",
            title = "ibis Bangkok Riverside",
            description = "We'd like to hear from you!",
            coverImageUrl = "https://dhdbhh0jsld0o.cloudfront.net/m/287db81c5e4242412cc0_"
        )
    )
}
