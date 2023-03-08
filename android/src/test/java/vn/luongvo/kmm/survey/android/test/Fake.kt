package vn.luongvo.kmm.survey.android.test

import co.nimblehq.jsonapi.json.JsonApi
import kotlinx.serialization.json.Json
import vn.luongvo.kmm.survey.data.remote.model.response.SurveyResponse
import vn.luongvo.kmm.survey.data.remote.model.response.toSurvey
import vn.luongvo.kmm.survey.domain.model.*

object Fake {

    private val json = Json {
        prettyPrint = true
        isLenient = true
        encodeDefaults = true
        ignoreUnknownKeys = true
    }

    private inline fun <reified T> decodeFromJsonApiString(path: String): T {
        return JsonApi(json).decodeFromJsonApiString(Resource(path).readText())
    }

    val token = Token(
        tokenType = "tokenType",
        accessToken = "accessToken",
        refreshToken = "refreshToken"
    )

    val user = User(
        email = "email",
        name = "name",
        avatarUrl = "avatarUrl"
    )

    val surveys = listOf(
        Survey(
            id = "1",
            title = "Scarlett Bangkok",
            description = "We'd love to hear from you!",
            coverImageUrl = "https://dhdbhh0jsld0o.cloudfront.net/m/1ea51560991bcb7d00d0_",
            questions = null
        ),
        Survey(
            id = "2",
            title = "ibis Bangkok Riverside",
            description = "We'd like to hear from you!",
            coverImageUrl = "https://dhdbhh0jsld0o.cloudfront.net/m/287db81c5e4242412cc0_",
            questions = null
        )
    )

    val cachedSurveys = listOf(
        Survey(
            id = "1",
            title = "Scarlett Bangkok",
            description = "We'd love to hear from you!",
            coverImageUrl = "https://dhdbhh0jsld0o.cloudfront.net/m/1ea51560991bcb7d00d0_",
            questions = null
        )
    )

    val surveyDetail = decodeFromJsonApiString<SurveyResponse>("survey_detail.json").toSurvey()
}
