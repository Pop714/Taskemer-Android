package net.pop.taskemer.ui.screens.task

data class TaskUiModel(
    val id: String,
    val title: String,
    val status: TaskStatus,
    val lastUpdated: String,
    val hasVoiceNote: Boolean,
    val hasAttachment: Boolean
)
