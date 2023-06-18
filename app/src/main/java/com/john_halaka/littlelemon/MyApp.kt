package com.john_halaka.littlelemon

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.john_halaka.littlelemon.data.MenuItemDatabase
import com.john_halaka.littlelemon.data.MenuRepository

class MyApp : Application() {
    private val database by lazy { MenuItemDatabase.getInstance(this) }
    val repository by lazy { MenuRepository(database.menuItemDao(), this) }

    val viewModelFactory by lazy {
        ViewModelProvider.AndroidViewModelFactory.getInstance(this)
    }
}