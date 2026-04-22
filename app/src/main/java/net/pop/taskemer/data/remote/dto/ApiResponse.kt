package net.pop.taskemer.data.remote.dto

data class ApiResponse<T>(
    val message: String,
    val statusCode: Int,
    val data: T
)