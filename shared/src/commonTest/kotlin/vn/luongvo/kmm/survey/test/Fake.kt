package vn.luongvo.kmm.survey.test

import vn.luongvo.kmm.survey.data.remote.model.response.TokenResponse
import vn.luongvo.kmm.survey.data.remote.model.response.toToken

object Fake {

    val tokenResponse = TokenResponse(
        tokenType = "tokenType",
        accessToken = "accessToken",
        refreshToken = "refreshToken"
    )

    val token = tokenResponse.toToken()
}
