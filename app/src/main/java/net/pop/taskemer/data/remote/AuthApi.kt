package net.pop.taskemer.data.remote

import net.pop.taskemer.data.remote.dto.ApiResponse
import net.pop.taskemer.data.remote.dto.LoginRequest
import net.pop.taskemer.data.remote.dto.AuthResponse
import net.pop.taskemer.data.remote.dto.RegisterRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("api/v1/auth/authenticate")
    suspend fun login(@Body request: LoginRequest): Response<ApiResponse<AuthResponse>>

    @POST("api/v1/auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<ApiResponse<AuthResponse>>
}