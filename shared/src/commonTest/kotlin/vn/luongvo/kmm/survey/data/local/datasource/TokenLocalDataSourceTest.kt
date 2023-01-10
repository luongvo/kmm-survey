package vn.luongvo.kmm.survey.data.local.datasource

import com.russhwolf.settings.Settings
import com.russhwolf.settings.set
import io.kotest.matchers.shouldBe
import io.mockative.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import vn.luongvo.kmm.survey.test.Fake.token
import kotlin.test.BeforeTest
import kotlin.test.Test

@ExperimentalCoroutinesApi
class TokenLocalDataSourceTest {

    @Mock
    private val mockSettings = mock(Settings::class)

    private lateinit var dataSource: TokenLocalDataSource

    @BeforeTest
    fun setUp() {
        dataSource = TokenLocalDataSourceImpl(mockSettings)
    }

    @Test
    fun `when saving token data - it saves token data correctly`() = runTest {
        dataSource.saveToken(token)

        verify(mockSettings)
            .invocation { set("tokenType", "Bearer") }
            .wasInvoked(exactly = 1.time)
        verify(mockSettings)
            .invocation { set("accessToken", "lbxD2K2BjbYtNzz8xjvh2FvSKx838KBCf79q773kq2c") }
            .wasInvoked(exactly = 1.time)
        verify(mockSettings)
            .invocation { set("refreshToken", "3zJz2oW0njxlj_I3ghyUBF7ZfdQKYXd2n0ODlMkAjHc") }
            .wasInvoked(exactly = 1.time)
    }

    @Test
    fun `when getting token data - it returns token data correctly`() = runTest {
        given(mockSettings)
            .invocation { mockSettings.getString("tokenType", "") }
            .thenReturn("tokenType")
        given(mockSettings)
            .invocation { mockSettings.getString("accessToken", "") }
            .thenReturn("accessToken")
        given(mockSettings)
            .invocation { mockSettings.getString("refreshToken", "") }
            .thenReturn("refreshToken")

        dataSource.tokenType shouldBe "tokenType"
        dataSource.accessToken shouldBe "accessToken"
        dataSource.refreshToken shouldBe "refreshToken"
    }

    @Test
    fun `when clearing token data - it clears token data correctly`() = runTest {
        dataSource.clear()

        verify(mockSettings)
            .function(mockSettings::clear)
            .wasInvoked(exactly = 1.time)
    }
}
