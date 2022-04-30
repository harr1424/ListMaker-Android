package com.harr1424.listmaker

import android.app.Application
import com.harr1424.listmaker.data.AppDatabase

/**
 * An application class that inherits from [Application], allows for the creation of a singleton
 * instance of the AppDatabase
 */
class BaseApplication : Application() {
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
}