package com.john_halaka.littlelemon

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(navController: NavHostController) {
        Text(text = "This is HomeScreen",
        fontSize = 32.sp)
}