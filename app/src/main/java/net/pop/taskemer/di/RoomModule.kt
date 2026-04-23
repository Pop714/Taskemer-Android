package net.pop.taskemer.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton
import net.pop.taskemer.data.local.TaskemerDatabase
import net.pop.taskemer.data.local.dao.ProjectDao
import kotlin.jvm.java

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideTaskemerDatabase(
        @ApplicationContext context: Context
    ): TaskemerDatabase {
        return Room.databaseBuilder(
            context,
            TaskemerDatabase::class.java,
            "taskemer_app_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideProjectDao(database: TaskemerDatabase): ProjectDao {
        return database.projectDao()
    }
}