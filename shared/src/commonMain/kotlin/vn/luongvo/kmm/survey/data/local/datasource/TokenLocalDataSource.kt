package vn.luongvo.kmm.survey.data.local.datasource

import com.russhwolf.settings.*
import vn.luongvo.kmm.survey.domain.model.Token

private const val TOKEN_TYPE_KEY = "tokenType"
private const val ACCESS_TOKEN_KEY = "accessToken"
private const val REFRESH_TOKEN_KEY = "refreshToken"

interface TokenLocalDataSource {
    fun saveToken(token: Token)

    fun clear()

    val tokenType: String

    val accessToken: String

    val refreshToken: String
}

class TokenLocalDataSourceImpl(private val settings: Settings) : TokenLocalDataSource {

    override fun saveToken(token: Token) = with(token) {
        settings[TOKEN_TYPE_KEY] = tokenType
        settings[ACCESS_TOKEN_KEY] = accessToken
        settings[REFRESH_TOKEN_KEY] = refreshToken
    }

    override fun clear() {
        settings.clear()
    }

    override val tokenType: String
        get() = settings.getString(TOKEN_TYPE_KEY, "")

    override val accessToken: String
        get() = settings.getString(ACCESS_TOKEN_KEY, "")

    override val refreshToken: String
        get() = settings.getString(REFRESH_TOKEN_KEY, "")
}
