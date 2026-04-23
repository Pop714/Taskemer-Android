package net.pop.taskemer.domain.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.pop.taskemer.data.local.TokenManager
import net.pop.taskemer.data.remote.dto.LoginRequest
import net.pop.taskemer.data.remote.dto.RegisterRequest
import javax.inject.Inject
import net.pop.taskemer.data.remote.AuthApi

class AuthRepository @Inject constructor(
    private val api: AuthApi,
    private val tokenManager: TokenManager
) {

    suspend fun login(request: LoginRequest): Result<String> = withContext(Dispatchers.IO) {
        try {
            val response = api.login(request)
            if (response.isSuccessful && response.body() != null) {
                val token = response.body()!!.data.token
                val userId = response.body()!!.data.userId
                tokenManager.saveAuthData(token, request.username, userId)
                Result.success("Login Successful")
            } else {
                Result.failure(Exception("Invalid credentials"))
            }
        } catch (e: Exception) {
            Result.failure(Exception("Network error: ${e.message}"))
        }
    }

    suspend fun register(request: RegisterRequest): Result<String> = withContext(Dispatchers.IO) {
        try {
            val response = api.register(request)
            if (response.isSuccessful && response.body() != null) {
                val token = response.body()!!.data.token
                val userId = response.body()!!.data.userId
                tokenManager.saveAuthData(token, request.username, userId)
                Result.success("Registration Successful")
            } else {
                Result.failure(Exception("Registration failed. Username may exist."))
            }
        } catch (e: Exception) {
            Result.failure(Exception("Network error: ${e.message}"))
        }
    }
}