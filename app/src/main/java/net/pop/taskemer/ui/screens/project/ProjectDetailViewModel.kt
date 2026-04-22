package net.pop.taskemer.ui.screens.project

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class ProjectDetailViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(ProjectDetailState())
    val uiState: StateFlow<ProjectDetailState> = _uiState.asStateFlow()
}