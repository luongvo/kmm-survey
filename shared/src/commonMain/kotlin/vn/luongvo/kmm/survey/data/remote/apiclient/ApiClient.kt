package vn.luongvo.kmm.survey.data.remote.apiclient

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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json

class ApiClient(
    engine: HttpClientEngine,
) {

    val httpClient: HttpClient

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
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        encodeDefaults = true
                        ignoreUnknownKeys = true
                    }
                )
            }
        }
    }

    inline fun <reified T> body(builder: HttpRequestBuilder): Flow<T> {
        return flow {
            val data = httpClient.request(
                builder.apply {
                    contentType(ContentType.Application.Json)
                }
            ).bodyAsText()
            // TODO Parse JSON:API here
            emit(data as T)
        }
    }
}
