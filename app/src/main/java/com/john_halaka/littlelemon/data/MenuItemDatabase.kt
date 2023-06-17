package com.john_halaka.littlelemon.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [MenuItem::class], version =1 , exportSchema = false)
abstract class MenuItemDatabase : RoomDatabase(){
    abstract fun menuItemDao (): MenuItemDao


    companion object {
        @Volatile
        private var INSTANCE: MenuItemDatabase? = null

        fun getInstance(context: Context): MenuItemDatabase {
            val tempInstance = INSTANCE

            if (tempInstance !=null) {

                return tempInstance
            } else
                synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        MenuItemDatabase::class.java,
                        name = "menuItem_database"
                    ) .build()

                    INSTANCE = instance
                    return instance

            }

        }
    }
}