package vn.luongvo.kmm.survey.data.remote.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import vn.luongvo.kmm.survey.BuildKonfig

private const val GRANT_TYPE_PASSWORD = "password"

@Serializable
data class LoginRequestBody(
    @SerialName("grant_type")
    val grantType: String = GRANT_TYPE_PASSWORD,
    @SerialName("email")
    val email: String,
    @SerialName("password")
    val password: String,
    @SerialName("client_id")
    val clientId: String = BuildKonfig.CLIENT_ID,
    @SerialName("client_secret")
    val clientSecret: String = BuildKonfig.CLIENT_SECRET,
)
