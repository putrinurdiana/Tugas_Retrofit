package com.example.tugasretrofit.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlin.collections.List

@Dao
interface ListDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(list: FavoritList)
    @Update
    fun update(note: FavoritList)
    @Delete
    fun delete(note: FavoritList)
    @get:Query("SELECT*from table_list ORDER BY id ASC")
    val allNotes: LiveData<List<FavoritList>>
}