package vn.luongvo.kmm.survey.data.remote

import co.nimblehq.jsonapi.json.JsonApi
import io.github.aakira.napier.*
import io.github.aakira.napier.LogLevel
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.logging.LogLevel.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.flow.last
import kotlinx.serialization.json.Json
import vn.luongvo.kmm.survey.BuildKonfig
import vn.luongvo.kmm.survey.data.extensions.path
import vn.luongvo.kmm.survey.data.local.datasource.TokenLocalDataSource
import vn.luongvo.kmm.survey.domain.usecase.RefreshTokenUseCase

class ApiClient(
    engine: HttpClientEngine,
    tokenLocalDataSource: TokenLocalDataSource? = null,
    refreshTokenUseCase: RefreshTokenUseCase? = null
) {

    val httpClient: HttpClient
    val json = Json {
        prettyPrint = true
        isLenient = true
        encodeDefaults = true
        ignoreUnknownKeys = true
    }

    init {
        Napier.takeLogarithm()
        Napier.base(DebugAntilog())
        httpClient = HttpClient(engine = engine) {
            install(Logging) {
                level = ALL
                logger = object : Logger {
                    override fun log(message: String) {
                        Napier.log(LogLevel.DEBUG, message = message)
                    }
                }
            }

            install(ContentNegotiation) {
                json(json)
            }

            if (tokenLocalDataSource != null && refreshTokenUseCase != null) {
                install(Auth) {
                    bearer {
                        loadTokens {
                            BearerTokens(tokenLocalDataSource.accessToken, tokenLocalDataSource.refreshToken)
                        }

                        refreshTokens {
                            val token = refreshTokenUseCase(refreshToken = oldTokens?.refreshToken ?: "")
                                .last()
                            BearerTokens(token.accessToken, token.refreshToken)
                        }

                        sendWithoutRequest { request ->
                            request.url.host == Url(BuildKonfig.BASE_URL).host
                        }
                    }
                }
            }
        }
    }

    suspend inline fun <reified T> get(path: String): T =
        request(path, HttpMethod.Get)

    suspend inline fun <reified T> post(path: String, requestBody: Any): T =
        request(path, HttpMethod.Post, requestBody)

    suspend inline fun <reified T> request(path: String, method: HttpMethod, requestBody: Any? = null): T {
        val body = httpClient.request(
            HttpRequestBuilder().apply {
                this.method = method
                path(path)
                requestBody?.let { setBody(requestBody) }
                contentType(ContentType.Application.Json)
            }
        ).bodyAsText()
        return JsonApi(json).decodeFromJsonApiString(body)
    }
}
