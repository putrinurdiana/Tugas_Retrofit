package com.example.tugasretrofit.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase

@Database(entities = [FavoritList::class], version = 1, exportSchema = false)
abstract class FavListDatabase : RoomDatabase() {
    abstract fun listDao() : ListDao ?
    companion object {
        @Volatile
        private var INSTANCE : FavListDatabase ? = null
        fun getDatabase(context: Context) : FavListDatabase? {
            if (INSTANCE == null) {
                synchronized(FavListDatabase::class.java) {
                    INSTANCE = databaseBuilder(
                        context.applicationContext,
                        FavListDatabase::class.java, "table_list"
                    )
                        .build()
                }
            }
            return INSTANCE
        }
    }

}