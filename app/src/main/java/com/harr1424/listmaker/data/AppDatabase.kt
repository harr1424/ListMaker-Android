package com.harr1424.listmaker.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MainItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mainItemDao(): MainItemDao
    abstract fun detailItemDao(): DetailItemDao
}