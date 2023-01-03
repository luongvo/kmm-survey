package vn.luongvo.kmm.survey.data.remote.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import vn.luongvo.kmm.survey.BuildKonfig

@Serializable
data class LogoutRequestBody(
    @SerialName("token")
    val accessToken: String,
    @SerialName("client_id")
    val clientId: String = BuildKonfig.CLIENT_ID,
    @SerialName("client_secret")
    val clientSecret: String = BuildKonfig.CLIENT_SECRET,
)
