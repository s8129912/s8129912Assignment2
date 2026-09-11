package com.example.s8129912assignment2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {
    private val _dashboardState = MutableLiveData<NetworkResult<List<Entity>>>()
    val dashboardState: LiveData<NetworkResult<List<Entity>>> = _dashboardState

    fun fetchDashboardData(keypass: String) {
        viewModelScope.launch {
            _dashboardState.value = NetworkResult.Loading
            try {
                val response = apiService.getDashboardData(keypass)
                if (response.isSuccessful && response.body() != null) {
                    val entitiesList = response.body()!!.entities

                    // 🪵 PASTE THIS DIAGNOSTIC LINE HERE:
                    android.util.Log.d("API_CHECK", "First item data structure: ${entitiesList.firstOrNull()}")

                    _dashboardState.value = NetworkResult.Success(entitiesList)
                } else {
                    _dashboardState.value = NetworkResult.Error("Failed to fetch data: ${response.message()}")
                }
            } catch (e: Exception) {
                _dashboardState.value = NetworkResult.Error("Network Error: ${e.localizedMessage}")
            }
        }
    }
}