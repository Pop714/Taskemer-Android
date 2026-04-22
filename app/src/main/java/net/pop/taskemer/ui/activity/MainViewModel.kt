package net.pop.taskemer.ui.activity

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch
import net.pop.taskemer.data.local.TokenManager
import net.pop.taskemer.ui.navigation.Routes

@HiltViewModel
class MainViewModel @Inject constructor(
    private val tokenManager: TokenManager
) : ViewModel() {

    var isLoading by mutableStateOf(true)
        private set

    var startDestination by mutableStateOf(Routes.LOGIN)
        private set

    init {
        viewModelScope.launch {
            tokenManager.tokenFlow.collect { token ->
                startDestination = if (token.isNullOrBlank()) {
                    Routes.LOGIN
                } else {
                    Routes.MAIN
                }
                isLoading = false
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            tokenManager.clearAuthData()
        }
    }
}