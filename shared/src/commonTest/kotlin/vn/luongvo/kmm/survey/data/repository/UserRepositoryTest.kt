package vn.luongvo.kmm.survey.data.repository

import app.cash.turbine.test
import io.kotest.matchers.shouldBe
import io.mockative.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import vn.luongvo.kmm.survey.data.remote.datasource.UserRemoteDataSource
import vn.luongvo.kmm.survey.domain.repository.UserRepository
import vn.luongvo.kmm.survey.test.Fake.user
import vn.luongvo.kmm.survey.test.Fake.userResponse
import kotlin.test.BeforeTest
import kotlin.test.Test

@ExperimentalCoroutinesApi
class UserRepositoryTest {

    @Mock
    private val mockDataSource = mock(UserRemoteDataSource::class)

    private lateinit var repository: UserRepository

    @BeforeTest
    fun setUp() {
        repository = UserRepositoryImpl(
            mockDataSource
        )
    }

    @Test
    fun `when calling getUserProfile successfully - it returns user`() = runTest {
        given(mockDataSource)
            .suspendFunction(mockDataSource::getUserProfile)
            .whenInvoked()
            .thenReturn(userResponse)

        repository.getUserProfile().test {
            awaitItem() shouldBe user
            awaitComplete()
        }
    }

    @Test
    fun `when calling getUserProfile fails - it throws the corresponding error`() = runTest {
        val throwable = Throwable()
        given(mockDataSource)
            .suspendFunction(mockDataSource::getUserProfile)
            .whenInvoked()
            .thenThrow(throwable)

        repository.getUserProfile().test {
            awaitError() shouldBe throwable
        }
    }

    @Test
    fun `when calling clearClientTokenConfig - it executes clearClientTokenConfig method in the user data source`() =
        runTest {
            repository.clearClientTokenConfig()

            verify(mockDataSource)
                .function(mockDataSource::clearClientTokenConfig)
                .wasInvoked(exactly = 1.time)
        }
}
