package br.edu.ifsp.dmo.diario.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.edu.ifsp.dmo.diario.data.dao.DiaryEntryDao
import br.edu.ifsp.dmo.diario.data.model.DiaryEntry

class AppDatabase {

    @Database(entities = [DiaryEntry::class], version = 1)
    abstract class AppDatabase : RoomDatabase() {
        companion object {

            const val DATABASE_NAME = "app_diary.db"
            private lateinit var instance: AppDatabase

            fun getInstance(context: Context): AppDatabase {
                if (!::instance.isInitialized) {
                    synchronized(AppDatabase::class) {
                        instance = Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).build()
                    }
                }
                return instance
            }
        }
        abstract fun getDiaryEntryDao(): DiaryEntryDao
    }
}