package vn.luongvo.kmm.survey.domain.exceptions

data class ApiException(
    override val message: String?,
    override val cause: Throwable,
    val code: String?
) : RuntimeException(message, cause)
