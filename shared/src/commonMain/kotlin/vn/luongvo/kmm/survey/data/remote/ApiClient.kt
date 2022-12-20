package vn.luongvo.kmm.survey.data.remote

import co.nimblehq.jsonapi.json.JsonApi
import io.github.aakira.napier.*
import io.github.aakira.napier.LogLevel
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.logging.LogLevel.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import vn.luongvo.kmm.survey.data.extensions.path

class ApiClient(
    engine: HttpClientEngine,
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
        }
    }

    suspend inline fun <reified T> post(path: String, requestBody: Any): T {
        val body = httpClient.request(
            HttpRequestBuilder().apply {
                method = HttpMethod.Post
                path(path)
                setBody(requestBody)
                contentType(ContentType.Application.Json)
            }
        ).bodyAsText()
        return JsonApi(json).decodeFromJsonApiString(body)
    }
}
