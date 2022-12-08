package vn.luongvo.kmm.survey.data.remote.apiclient.builder

import io.ktor.client.request.*
import vn.luongvo.kmm.survey.BuildKonfig

fun HttpRequestBuilder.path(path: String) {
    url(BuildKonfig.BASE_URL.plus(path))
}
