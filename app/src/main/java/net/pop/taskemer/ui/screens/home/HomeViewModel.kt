package net.pop.taskemer.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import net.pop.taskemer.data.local.TokenManager
import net.pop.taskemer.ui.navigation.Routes

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val tokenManager: TokenManager
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeState())
    val uiState: StateFlow<HomeState> = _uiState.asStateFlow()

    init {
        loadUsername()
    }

    private fun loadUsername() {
        viewModelScope.launch {
            tokenManager.usernameFlow.collect { username ->
                if (!username.isNullOrBlank()) {
                    _uiState.update { it.copy(username = username) }
                }
            }
        }
    }
}