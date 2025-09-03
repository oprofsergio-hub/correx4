package com.correxapp.data.database


import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.correxapp.data.source.local.dao.ClassDao
import com.correxapp.domain.model.ClassModel
import com.correxapp.domain.model.StudentModel

@Database(
    entities = [ClassModel::class, StudentModel::class],
    version = 2, // Mudou, pois incluímos StudentModel
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun classDao(): ClassDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "correx_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
