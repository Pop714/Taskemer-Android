package net.pop.taskemer.ui.screens.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import net.pop.taskemer.data.remote.dto.RegisterRequest
import net.pop.taskemer.domain.repository.AuthRepository

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterState())
    val uiState: StateFlow<RegisterState> = _uiState.asStateFlow()

    fun onFirstnameChange(firstname: String) {
        _uiState.update { it.copy(firstname = firstname) }
    }

    fun onLastnameChange(lastname: String) {
        _uiState.update { it.copy(lastname = lastname) }
    }

    fun onUsernameChange(username: String) {
        _uiState.update { it.copy(username = username) }
    }

    fun onPasswordChange(password: String) {
        val strength = calculatePasswordStrength(password)
        val isMismatch =
            _uiState.value.confirmPassword.isNotEmpty() && password != _uiState.value.confirmPassword
        _uiState.update {
            it.copy(
                password = password,
                passwordStrength = strength,
                passwordMismatchError = isMismatch
            )
        }
    }

    fun onConfirmPasswordChange(confirmPassword: String) {
        val isMismatch = confirmPassword.isNotEmpty() && confirmPassword != _uiState.value.password
        _uiState.update {
            it.copy(
                confirmPassword = confirmPassword,
                passwordMismatchError = isMismatch
            )
        }
    }

    private fun calculatePasswordStrength(password: String): PasswordStrength {
        if (password.isEmpty()) return PasswordStrength.NONE
        if (password.length < 6) return PasswordStrength.WEAK

        var score = 0
        if (password.length >= 8) score++
        if (password.any { it.isDigit() }) score++
        if (password.any { it.isUpperCase() }) score++
        if (password.any { !it.isLetterOrDigit() }) score++ // Special character

        return when {
            score <= 1 -> PasswordStrength.WEAK
            score == 2 || score == 3 -> PasswordStrength.FAIR
            else -> PasswordStrength.STRONG
        }
    }

    fun register() {
        // Implement Spring Boot API call here
        val currentState = _uiState.value

        // Basic validation
        if (currentState.passwordMismatchError || currentState.password.isBlank() || currentState.firstname.isBlank()
            || currentState.username.isBlank()
        ) {
            _uiState.update { it.copy(isLoading = false, errorMessage = "Empty Fields!") }
            return
        }

        _uiState.update { it.copy(isLoading = true, errorMessage = null) }

        viewModelScope.launch {
            val request = RegisterRequest(
                firstname = currentState.firstname,
                lastname = currentState.lastname,
                username = currentState.username,
                password = currentState.password
            )

            val result = repository.register(request)

            result.onSuccess {
                _uiState.update { it.copy(isLoading = false, registerSuccess = true) }
            }.onFailure { error ->
                _uiState.update { it.copy(isLoading = false, errorMessage = error.message) }
            }
        }
    }
}