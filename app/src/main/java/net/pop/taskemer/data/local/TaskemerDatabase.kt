package net.pop.taskemer.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import net.pop.taskemer.data.local.dao.ProjectDao
import net.pop.taskemer.data.local.entity.ProjectEntity
import net.pop.taskemer.data.local.entity.TaskEntity

@Database(
    entities = [ProjectEntity::class, TaskEntity::class],
    version = 1,
    exportSchema = false
)
abstract class TaskemerDatabase : RoomDatabase() {
    abstract fun projectDao(): ProjectDao
}