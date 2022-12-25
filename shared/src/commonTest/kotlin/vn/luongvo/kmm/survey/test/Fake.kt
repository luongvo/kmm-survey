package vn.luongvo.kmm.survey.test

import co.nimblehq.jsonapi.model.JsonApiError
import co.nimblehq.jsonapi.model.JsonApiException
import vn.luongvo.kmm.survey.data.remote.model.response.TokenResponse
import vn.luongvo.kmm.survey.data.remote.model.response.toToken

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
}
