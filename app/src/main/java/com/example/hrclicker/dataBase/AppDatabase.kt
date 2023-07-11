package com.example.hrclicker.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.hrclicker.dataBase.converters.IntArrayConverter
import com.example.hrclicker.dataBase.converters.ObjectConverter
import com.example.hrclicker.dataBase.converters.StringArrayConverter


@Database(entities = [User::class], version = 1 )
@TypeConverters(StringArrayConverter::class, IntArrayConverter::class, ObjectConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getInstance (context: Context):
                AppDatabase {
            return INSTANCE ?: synchronized (this) {
                val instance =
                    Room.databaseBuilder (
                        context.applicationContext ,
                        AppDatabase::class.java,
                        "my-app-db"
                    ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}