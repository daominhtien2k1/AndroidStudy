package com.example.mvvmtodo.di

import android.content.Context
import androidx.room.Room
import com.example.mvvmtodo.data.TaskDao
import com.example.mvvmtodo.data.TaskDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    /*
        Không phải là inject chéo, hình như là trước khi addCallback đã có biến TaskDatabase rồi
        https://stackoverflow.com/questions/50520840/what-is-the-proper-way-to-implement-addcallback-when-providing-roomdatabase-v#
    */
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context, callback: TaskDatabase.Callback): TaskDatabase
        = Room.databaseBuilder(context, TaskDatabase::class.java, "task_database")
            .fallbackToDestructiveMigration()
            .addCallback(callback)
            .build()

    @Provides
    fun provideTaskDao(db: TaskDatabase): TaskDao = db.taskDao()

    @ApplicationScope
    @Provides
    @Singleton
    fun provideApplicationScope(): CoroutineScope = CoroutineScope(SupervisorJob())
}

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope