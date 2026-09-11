package com.example.s8129912assignment2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {

    private val _loginResult = MutableLiveData<Result<String>>()
    val loginResult: LiveData<Result<String>> = _loginResult

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun login(user: String, pass: String) {
        // Validate blank entries before wasting network resources
        if (user.isBlank() || pass.isBlank()) {
            _loginResult.value = Result.failure(Exception("Fields cannot be empty"))
            return
        }

        viewModelScope.launch {
            _isLoading.value = true
            try {
                // Network POST execution using Coroutines
                val response = apiService.loginUser(LoginRequest(user, pass))
                if (response.isSuccessful && response.body() != null) {
                    _loginResult.value = Result.success(response.body()!!.keypass)
                } else {
                    _loginResult.value = Result.failure(Exception("Login failed. Check your ID or Password."))
                }
            } catch (e: Exception) {
                _loginResult.value = Result.failure(Exception("Network Error: ${e.localizedMessage}"))
            } finally {
                _isLoading.value = false
            }
        }
    }
}
