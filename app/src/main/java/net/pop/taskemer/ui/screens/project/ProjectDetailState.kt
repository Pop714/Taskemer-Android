package net.pop.taskemer.ui.screens.project

import net.pop.taskemer.ui.screens.task.TaskStatus
import net.pop.taskemer.ui.screens.task.TaskUiModel

data class ProjectDetailState(
    val projectTitle: String = "Cloud Architecture Refactor",
    val projectDescription: String = "System-wide migration to serverless edge functions and regional database distribution for low-latency global access.",
    val taskCount: Int = 12,
    val deadline: String = "2 Days",
    val activeDuration: String = "14 Days",
    val projectStatus: String = "On Track",
    val tasks: List<TaskUiModel> = listOf(
        TaskUiModel(
            "1",
            "API Endpoint Documentation Expansion",
            TaskStatus.ONGOING,
            "Updated 14m ago",
            true,
            true
        ),
        TaskUiModel(
            "2",
            "Security Audit - Q3 Compliance",
            TaskStatus.ENDED,
            "Finalized Oct 24",
            false,
            true
        ),
        TaskUiModel(
            "3",
            "Implement Redis Caching Layer",
            TaskStatus.ONGOING,
            "Updated 2h ago",
            true,
            false
        ),
        TaskUiModel(
            "4",
            "Hotfix: Webhook Timeout on Prod-3",
            TaskStatus.ARCHIVED,
            "Due in 4h",
            false,
            false
        )
    )
)
