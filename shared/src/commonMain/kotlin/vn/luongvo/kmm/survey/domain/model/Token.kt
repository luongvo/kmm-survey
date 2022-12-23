package vn.luongvo.kmm.survey.domain.model

data class Token(
    val tokenType: String,
    val accessToken: String,
    val refreshToken: String
)
