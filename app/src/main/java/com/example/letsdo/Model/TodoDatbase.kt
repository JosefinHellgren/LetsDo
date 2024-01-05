package com.example.letsdo.Model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Todo::class), version = 1)
abstract class TodoDatabase: RoomDatabase() {
    abstract fun getTodoDao(): TodoDAOInterface

    companion object {
        @Volatile
        private var INSTANCE: TodoDatabase? = null

        fun getDatabase (context: Context): TodoDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TodoDatbase::class.java,
                   TodoDatabase
                ).build
            }

        }
     }
}