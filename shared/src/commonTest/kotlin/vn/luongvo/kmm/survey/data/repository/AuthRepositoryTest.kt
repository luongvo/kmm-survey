package vn.luongvo.kmm.survey.data.repository

import app.cash.turbine.test
import io.kotest.matchers.shouldBe
import io.mockative.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import vn.luongvo.kmm.survey.data.local.datasource.TokenLocalDataSource
import vn.luongvo.kmm.survey.data.remote.datasource.AuthRemoteDataSource
import vn.luongvo.kmm.survey.domain.repository.AuthRepository
import vn.luongvo.kmm.survey.test.Fake.token
import vn.luongvo.kmm.survey.test.Fake.tokenResponse
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
            awaitItem() shouldBe token
            awaitComplete()
        }
    }

    @Test
    fun `when calling logIn fails - it throws the corresponding error`() = runTest {
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
    fun `when calling refreshToken successfully - it returns token`() = runTest {
        given(mockAuthRemoteDataSource)
            .suspendFunction(mockAuthRemoteDataSource::refreshToken)
            .whenInvokedWith(any())
            .thenReturn(tokenResponse)

        repository.refreshToken("refreshToken").test {
            awaitItem() shouldBe token
            awaitComplete()
        }
    }

    @Test
    fun `when calling refreshToken fails - it throws the corresponding error`() = runTest {
        val throwable = Throwable()
        given(mockAuthRemoteDataSource)
            .suspendFunction(mockAuthRemoteDataSource::refreshToken)
            .whenInvokedWith(any())
            .thenThrow(throwable)

        repository.refreshToken("refreshToken").test {
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

    @Test
    fun `when calling isLoggedIn with valid token - it returns true`() = runTest {
        given(mockTokenLocalDataSource)
            .invocation { mockTokenLocalDataSource.tokenType }
            .thenReturn("tokenType")
        given(mockTokenLocalDataSource)
            .invocation { mockTokenLocalDataSource.accessToken }
            .thenReturn("accessToken")

        repository.isLoggedIn.test {
            awaitItem() shouldBe true
            awaitComplete()
        }
    }

    @Test
    fun `when calling isLoggedIn with invalid or non-exist token - it returns false`() = runTest {
        given(mockTokenLocalDataSource)
            .invocation { mockTokenLocalDataSource.tokenType }
            .thenReturn("tokenType")
        given(mockTokenLocalDataSource)
            .invocation { mockTokenLocalDataSource.accessToken }
            .thenReturn("")

        repository.isLoggedIn.test {
            awaitItem() shouldBe false
            awaitComplete()
        }

        given(mockTokenLocalDataSource)
            .invocation { mockTokenLocalDataSource.tokenType }
            .thenReturn("")
        given(mockTokenLocalDataSource)
            .invocation { mockTokenLocalDataSource.accessToken }
            .thenReturn("accessToken")

        repository.isLoggedIn.test {
            awaitItem() shouldBe false
            awaitComplete()
        }
    }

    @Test
    fun `when calling logOut successfully - it returns Unit`() = runTest {
        given(mockAuthRemoteDataSource)
            .suspendFunction(mockAuthRemoteDataSource::logOut)
            .whenInvokedWith(any())
            .thenReturn(Unit)
        given(mockTokenLocalDataSource)
            .invocation { mockTokenLocalDataSource.accessToken }
            .thenReturn("accessToken")

        repository.logOut().test {
            awaitItem() shouldBe Unit
            awaitComplete()
        }
    }

    @Test
    fun `when calling logOut fails - it throws the corresponding error`() = runTest {
        val throwable = Throwable()
        given(mockAuthRemoteDataSource)
            .suspendFunction(mockAuthRemoteDataSource::logOut)
            .whenInvokedWith(any())
            .thenThrow(throwable)
        given(mockTokenLocalDataSource)
            .invocation { mockTokenLocalDataSource.accessToken }
            .thenReturn("accessToken")

        repository.logOut().test {
            awaitError() shouldBe throwable
        }
    }

    @Test
    fun `when calling clearToken - it executes the local data source`() = runTest {
        repository.clearToken()

        verify(mockTokenLocalDataSource)
            .function(mockTokenLocalDataSource::clear)
            .wasInvoked(exactly = 1.time)
    }
}
