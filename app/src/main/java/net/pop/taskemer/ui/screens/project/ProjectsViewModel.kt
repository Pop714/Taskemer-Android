package net.pop.taskemer.ui.screens.project

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import net.pop.taskemer.data.local.TokenManager
import net.pop.taskemer.data.local.dao.ProjectDao
import net.pop.taskemer.data.local.entity.ProjectEntity
import net.pop.taskemer.data.local.enums.ProjectPriority
import net.pop.taskemer.data.local.enums.ProjectStatus
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class ProjectsViewModel @Inject constructor(
    private val projectDao: ProjectDao,
    private val tokenManager: TokenManager
) : ViewModel() {

    // Automatically observe Room. If the DB changes, the UI re-renders instantly.
    val projects: StateFlow<List<ProjectEntity>> = projectDao.getUserProjects(11)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addProject(
        title: String,
        description: String,
        priority: ProjectPriority,
        status: ProjectStatus,
        deadline: String
    ) {
        viewModelScope.launch {
            val newProject = ProjectEntity(
                title = title,
                description = description,
                priority = priority,
                status = status,
                userId = 11,
                deadline = deadline,
                createdAt = LocalDateTime.now().toString()
            )
            // Save to local database (Offline-First!)
            projectDao.insertProject(newProject)

            // TODO: In the future, you would also trigger an API call here to sync to your Spring Boot backend.
            // If the API fails, it's fine! The data is already saved locally in Room.
        }
    }
}