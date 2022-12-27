package vn.luongvo.kmm.survey.data.extensions

import co.nimblehq.jsonapi.model.JsonApiException
import kotlinx.coroutines.flow.*
import vn.luongvo.kmm.survey.domain.exceptions.ApiException
import kotlin.experimental.ExperimentalTypeInference

@OptIn(ExperimentalTypeInference::class)
fun <T> flowTransform(@BuilderInference block: suspend FlowCollector<T>.() -> T) = flow {
    runCatching { block() }
        .onSuccess { emit(it) }
        .onFailure { throw it.mapError() }
}

private fun Throwable.mapError(): Throwable =
    when (this) {
        is JsonApiException -> ApiException(
            code = errors.first().code,
            message = errors.first().detail,
            cause = this
        )
        else -> this
    }
