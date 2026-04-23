package net.pop.taskemer.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import net.pop.taskemer.data.local.entity.TaskEntity
import net.pop.taskemer.ui.screens.task.TaskStatus

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(task: TaskEntity)

    @Delete
    fun deleteTask(task: TaskEntity)

    @Update
    fun changeStatus(task: TaskEntity)

    @Query("SELECT * FROM tasks WHERE projectId = :projectId ORDER BY status ASC")
    fun getProjectTasks(projectId: String)
}