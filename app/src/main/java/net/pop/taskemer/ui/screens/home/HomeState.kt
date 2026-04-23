package net.pop.taskemer.ui.screens.home

data class HomeState(
    val username: String = "",
    val activeProjects: Int = 3,
    val activeTasks: Int = 12,
    val archivedProjects: Int = 3,
    // Toggle this to 0 to test the empty state!
    val totalProjects: Int = 0
)
