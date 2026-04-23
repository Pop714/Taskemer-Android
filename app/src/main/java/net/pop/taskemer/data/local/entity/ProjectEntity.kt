package net.pop.taskemer.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import net.pop.taskemer.data.local.enums.ProjectPriority
import net.pop.taskemer.data.local.enums.ProjectStatus
import java.time.LocalDateTime
import java.util.UUID

@Entity(tableName = "projects")
data class ProjectEntity(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val userId: Int,
    val title: String,
    val description: String,
    val priority: ProjectPriority,
    val status: ProjectStatus,
    val deadline: String,
    val createdAt: String
)
