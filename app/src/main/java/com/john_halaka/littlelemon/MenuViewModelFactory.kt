package com.john_halaka.littlelemon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.john_halaka.littlelemon.data.MenuRepository

class MenuViewModelFactory (private val repository: MenuRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MenuViewModel::class.java)) {
            return MenuViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
