package vn.luongvo.kmm.survey.data.remote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import vn.luongvo.kmm.survey.domain.model.Token

// TODO implement a JSON:API parser
@Serializable
data class TokenResponse(
    @SerialName("data")
    val data: Data
) {

    @Serializable
    data class Data(
        @SerialName("id")
        val id: String,
        @SerialName("type")
        val type: String,
        @SerialName("attributes")
        val attributes: Attributes
    )

    @Serializable
    data class Attributes(
        @SerialName("token_type")
        val tokenType: String,
        @SerialName("access_token")
        val accessToken: String,
        @SerialName("refresh_token")
        val refreshToken: String
    )
}

fun TokenResponse.toToken() = with(data.attributes) {
    Token(
        tokenType = tokenType,
        accessToken = accessToken,
        refreshToken = refreshToken
    )
}
