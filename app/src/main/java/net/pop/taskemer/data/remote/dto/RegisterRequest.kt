package net.pop.taskemer.data.remote.dto

data class RegisterRequest(
    val firstname: String,
    val lastname: String,
    val username: String,
    val password: String
)