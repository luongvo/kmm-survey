package vn.luongvo.kmm.survey.data.repository

import io.kotest.matchers.shouldBe
import io.mockative.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runTest
import vn.luongvo.kmm.survey.data.remote.datasource.AuthRemoteDataSource
import vn.luongvo.kmm.survey.data.remote.model.response.toToken
import vn.luongvo.kmm.survey.domain.repository.AuthRepository
import vn.luongvo.kmm.survey.test.tokenResponse
import kotlin.test.BeforeTest
import kotlin.test.Test

@ExperimentalCoroutinesApi
class AuthRepositoryTest {

    @Mock
    private val mockAuthRemoteDataSource = mock(AuthRemoteDataSource::class)

    private lateinit var repository: AuthRepository

    @BeforeTest
    fun setUp() {
        repository = AuthRepositoryImpl(mockAuthRemoteDataSource)
    }

    @Test
    fun `when calling logIn successfully - it returns token`() = runTest {
        given(mockAuthRemoteDataSource)
            .suspendFunction(mockAuthRemoteDataSource::logIn)
            .whenInvokedWith(any())
            .thenReturn(tokenResponse)

        repository.logIn("email", "password").collect {
            it shouldBe tokenResponse.toToken()
        }
    }

    @Test
    fun `when calling logIn fails - it throws error`() = runTest {
        val mockThrowable = Throwable()
        given(mockAuthRemoteDataSource)
            .suspendFunction(mockAuthRemoteDataSource::logIn)
            .whenInvokedWith(any())
            .thenThrow(mockThrowable)

        repository.logIn("email", "password").catch {
            it shouldBe mockThrowable
        }.collect()
    }
}
