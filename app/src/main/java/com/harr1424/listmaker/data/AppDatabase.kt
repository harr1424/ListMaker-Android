package com.harr1424.listmaker.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MainItem::class, DetailItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mainItemDao(): MainItemDao
    abstract fun detailItemDao(): DetailItemDao
    companion object {
        @Volatile
        private var INSANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase{
            return INSANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "List Item Database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSANCE = instance
                return instance
            }
        }

    }
}