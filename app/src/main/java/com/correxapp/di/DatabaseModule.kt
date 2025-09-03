package com.correxapp.di

import android.content.Context
import androidx.room.Room
import com.correxapp.data.source.local.AppDatabase
import com.correxapp.data.source.local.dao.ClassDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "correx_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideClassDao(database: AppDatabase): ClassDao = database.classDao()
}
