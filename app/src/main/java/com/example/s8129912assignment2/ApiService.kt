package com.example.s8129912assignment2

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    // Login POST endpoint
    // IMPORTANT: Change "/footscray/auth" to "/sydney/auth" or "/br/auth" depending on your campus!
    @POST("footscray/auth")
    suspend fun loginUser(
        @Body request: LoginRequest,
    ): Response<LoginResponse>

    // Dashboard GET endpoint requiring the keypass token in the URL path
    @GET("dashboard/{keypass}")
    suspend fun getDashboardData(
        @Path("keypass") keypass: String
    ): Response<DashboardResponse>
}
