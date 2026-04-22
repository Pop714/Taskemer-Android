package net.pop.taskemer.ui.screens.focus

data class FocusState(
    val totalDurationSeconds: Long = 25 * 60,
    val timeRemainingSeconds: Long = 25 * 60,
    val isRunning: Boolean = false,
    val isDndEnabled: Boolean = false,
    val hasDndPermission: Boolean = false
)
