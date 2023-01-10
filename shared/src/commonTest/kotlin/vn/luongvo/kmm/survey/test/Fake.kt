package vn.luongvo.kmm.survey.test

import co.nimblehq.jsonapi.json.JsonApi
import co.nimblehq.jsonapi.model.JsonApiError
import co.nimblehq.jsonapi.model.JsonApiException
import com.goncalossilva.resources.Resource
import kotlinx.serialization.json.Json
import vn.luongvo.kmm.survey.data.remote.model.response.*

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

    val tokenResponse: TokenResponse =
        decodeFromJsonApiString("src/commonTest/resources/oauth_login.json")

    val token = tokenResponse.toToken()

    val userResponse: UserResponse =
        decodeFromJsonApiString("src/commonTest/resources/user.json")

    val user = userResponse.toUser()

    val surveyResponses: List<SurveyResponse> =
        decodeFromJsonApiString("src/commonTest/resources/surveys.json")

    val surveys = surveyResponses.map { it.toSurvey() }

    val surveyDetailResponse: SurveyResponse =
        decodeFromJsonApiString("src/commonTest/resources/survey_detail.json")

    val surveyDetail = surveyDetailResponse.toSurvey()

    val jsonApiException = JsonApiException(
        errors = listOf(
            JsonApiError(
                detail = "detail",
                code = "code"
            )
        )
    )
}
