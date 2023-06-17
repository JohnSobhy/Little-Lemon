package com.john_halaka.littlelemon

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
        repository.insertAll(menuItems)
    }

    init {

        viewModelScope.launch {
            val menuItems = repository.fetchMenuItems()
            _menuItems.value = repository.getMenuItems()

            if (menuItems != null) {
                insertAll(menuItems.menu)
            }
        }
    }
}

