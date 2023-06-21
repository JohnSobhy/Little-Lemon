package com.john_halaka.littlelemon

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.john_halaka.littlelemon.data.MenuItem
import com.john_halaka.littlelemon.data.MenuRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MenuViewModel (private val repository: MenuRepository) : ViewModel() {

    private val _menuItems = MutableStateFlow<List<MenuItem>>(emptyList())
    val menuItems: StateFlow<List<MenuItem>> = _menuItems

    fun insertAll(menuItems: List<MenuItem>) = viewModelScope.launch {
        Log.d("MenuViewModel", "insertAll called with menuItems: $menuItems")
        repository.insertAll(menuItems)
    }

    init {
        Log.d("MenuViewModel", "init called")
        viewModelScope.launch {
            val menuItems = repository.fetchMenuItems()
            Log.d("MenuViewModel", "menuItems: $menuItems")
            if (menuItems != null) {
                insertAll(menuItems.menu)
            }

            _menuItems.value = repository.getMenuItems()
            Log.d("MenuViewModel", "_menuItems.value: ${_menuItems.value}")

            val menuItemsFromDb = repository.getMenuItems()
            Log.d("MenuViewModel", "menuItemsFromDb: $menuItemsFromDb")
            _menuItems.value = menuItemsFromDb

        }
    }
}

