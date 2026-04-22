package net.pop.taskemer.ui.screens.auth.register

data class RegisterState(
    val firstname: String = "",
    val lastname: String = "",
    val username: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val passwordMismatchError: Boolean = false,
    val passwordStrength: PasswordStrength = PasswordStrength.NONE,
    val errorMessage: String? = null,
    val registerSuccess: Boolean = false,
    val isLoading: Boolean = false
)
