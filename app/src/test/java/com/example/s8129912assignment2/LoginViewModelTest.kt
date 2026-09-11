package com.example.s8129912assignment2

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var viewModel: LoginViewModel
    private val apiService = mock(ApiService::class.java)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = LoginViewModel(apiService)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun login_withEmptyFields_returnsFailure() = runTest {
        viewModel.login("", "")

        val state = viewModel.loginResult.value
        assertEquals(true, state?.isFailure)
    }

    @Test
    fun login_withValidFields_success_setsKeypass() = runTest {
        // Arrange
        val expectedKeypass = "test_keypass"
        val mockResponse = Response.success(LoginResponse(expectedKeypass))
        
        whenever(apiService.loginUser(any())).thenReturn(mockResponse)

        // Act
        viewModel.login("s8129912", "Vaishnavi")
        advanceUntilIdle()

        // Assert
        assertEquals(expectedKeypass, viewModel.loginResult.value?.getOrNull())
        assertEquals(false, viewModel.isLoading.value)
    }
}
