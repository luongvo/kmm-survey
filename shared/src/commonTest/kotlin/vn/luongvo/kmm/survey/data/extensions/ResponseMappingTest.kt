package vn.luongvo.kmm.survey.data.extensions

import app.cash.turbine.test
import io.kotest.matchers.shouldBe
import io.ktor.utils.io.errors.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import vn.luongvo.kmm.survey.domain.exceptions.ApiException
import vn.luongvo.kmm.survey.domain.model.Token
import vn.luongvo.kmm.survey.test.Fake.jsonApiException
import kotlin.test.Test

@ExperimentalCoroutinesApi
class ResponseMappingTest {

    @Test
    fun `When mapping API request flow failed with JsonApiException - it returns ApiException error`() = runTest {
        flowTransform<Token> {
            throw jsonApiException
        }.test {
            awaitError() shouldBe ApiException(
                message = "detail",
                code = "code",
                cause = jsonApiException
            )
        }
    }

    @Test
    fun `When mapping API request flow failed with a generic error - it returns that error`() = runTest {
        val error = IOException("no internet")
        flowTransform<Token> {
            throw error
        }.test {
            awaitError() shouldBe error
        }
    }
}
