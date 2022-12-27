package vn.luongvo.kmm.survey.data.remote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import vn.luongvo.kmm.survey.domain.model.Token

@Serializable
data class TokenResponse(
    @SerialName("token_type")
    val tokenType: String,
    @SerialName("access_token")
    val accessToken: String,
    @SerialName("refresh_token")
    val refreshToken: String
)

fun TokenResponse.toToken() = Token(
    tokenType = tokenType,
    accessToken = accessToken,
    refreshToken = refreshToken
)
