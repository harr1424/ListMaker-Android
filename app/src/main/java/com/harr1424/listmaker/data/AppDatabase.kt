package com.harr1424.listmaker.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.harr1424.listmaker.model.DetailItem
import com.harr1424.listmaker.model.MainItem

@Database(entities = [MainItem::class, DetailItem::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mainItemDao(): MainItemDao
    abstract fun detailItemDao(): DetailItemDao
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase{
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "List Item Database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}