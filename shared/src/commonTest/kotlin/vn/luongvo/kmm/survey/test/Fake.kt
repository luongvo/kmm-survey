package vn.luongvo.kmm.survey.test

import co.nimblehq.jsonapi.model.JsonApiError
import co.nimblehq.jsonapi.model.JsonApiException
import vn.luongvo.kmm.survey.data.remote.model.response.*

object Fake {

    val jsonApiException = JsonApiException(
        errors = listOf(
            JsonApiError(
                detail = "detail",
                code = "code"
            )
        )
    )

    val tokenResponse = TokenResponse(
        tokenType = "tokenType",
        accessToken = "accessToken",
        refreshToken = "refreshToken"
    )

    val token = tokenResponse.toToken()

    val userResponse = UserResponse(
        email = "luong@nimblehq.co",
        name = "Luong",
        avatarUrl = "https://secure.gravatar.com/avatar/8fae17b9d0c4cca18a9661bcdf650f23"
    )

    val user = userResponse.toUser()
}
