package net.pop.taskemer.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import net.pop.taskemer.data.local.enums.TaskStatus
import java.time.LocalDateTime
import java.util.UUID

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val createdAt: String,
    val status: TaskStatus,
    val title: String,
    val voicePath: String,
    val projectId: String
)
