package vn.luongvo.kmm.survey.data.repository

import app.cash.turbine.test
import io.kotest.matchers.shouldBe
import io.mockative.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import vn.luongvo.kmm.survey.data.local.datasource.TokenLocalDataSource
import vn.luongvo.kmm.survey.data.remote.datasource.AuthRemoteDataSource
import vn.luongvo.kmm.survey.data.remote.model.response.toToken
import vn.luongvo.kmm.survey.domain.repository.AuthRepository
import vn.luongvo.kmm.survey.test.token
import vn.luongvo.kmm.survey.test.tokenResponse
import kotlin.test.BeforeTest
import kotlin.test.Test

@ExperimentalCoroutinesApi
class AuthRepositoryTest {

    @Mock
    private val mockAuthRemoteDataSource = mock(AuthRemoteDataSource::class)

    @Mock
    private val mockTokenLocalDataSource = mock(TokenLocalDataSource::class)

    private lateinit var repository: AuthRepository

    @BeforeTest
    fun setUp() {
        repository = AuthRepositoryImpl(
            mockAuthRemoteDataSource,
            mockTokenLocalDataSource
        )
    }

    @Test
    fun `when calling logIn successfully - it returns token`() = runTest {
        given(mockAuthRemoteDataSource)
            .suspendFunction(mockAuthRemoteDataSource::logIn)
            .whenInvokedWith(any())
            .thenReturn(tokenResponse)

        repository.logIn("email", "password").test {
            awaitItem() shouldBe tokenResponse.toToken()
            awaitComplete()
        }
    }

    @Test
    fun `when calling logIn fails - it throws error`() = runTest {
        val throwable = Throwable()
        given(mockAuthRemoteDataSource)
            .suspendFunction(mockAuthRemoteDataSource::logIn)
            .whenInvokedWith(any())
            .thenThrow(throwable)

        repository.logIn("email", "password").test {
            awaitError() shouldBe throwable
        }
    }

    @Test
    fun `when saving token - it executes the local data source`() = runTest {
        repository.saveToken(token)
        verify(mockTokenLocalDataSource)
            .function(mockTokenLocalDataSource::saveToken)
            .with(eq(token))
            .wasInvoked(exactly = 1.time)
    }
}
