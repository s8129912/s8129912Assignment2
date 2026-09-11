package com.example.s8129912assignment2

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

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
        assertTrue("Expected failure result for empty fields", state?.isFailure == true)
    }

    @Test
    fun login_withValidFields_initiatesLoading() = runTest {
        viewModel.login("s8129912", "Vaishnavi")

        // Check if loading starts immediately
        assertTrue(viewModel.isLoading.value == true)
    }
}
