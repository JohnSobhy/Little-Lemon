package com.john_halaka.littlelemon.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface MenuItemDao {
    @Query("SELECT * FROM menu_items")
    suspend fun getAll(): List<MenuItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(menuItems: List<MenuItem>)
}