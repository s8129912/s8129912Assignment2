package com.example.s8129912assignment2

import java.io.Serializable

// 1. Data structure sent to the API during login
data class LoginRequest(
    val username: String, // Your Student ID
    val password: String, // Your First Name
)

// 2. Data structure received back from a successful login
data class LoginResponse(
    val keypass: String
)

// 3. Data structure received from the dashboard endpoint wrapper
data class DashboardResponse(
    val entities: List<Entity>,
    val entityTotal: Int
)

// 4. Individual item inside the entities list
// Implementing Serializable lets us easily send this object to the Details screen
// Temporarily change the model to catch all unknown keys in a map
data class Entity(
    val description: String,
    val name: String? = null,      // Guessing common names
    val type: String? = null,
    // This catches everything else the server sends:
    val extraFields: Map<String, Any>? = null
) : java.io.Serializable

sealed class NetworkResult<out T> {
    data class Success<out T>(val data: T) : NetworkResult<T>()
    data class Error(val message: String) : NetworkResult<Nothing>()
    object Loading : NetworkResult<Nothing>()
}

