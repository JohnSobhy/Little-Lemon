package com.john_halaka.littlelemon

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val markaziText = FontFamily(
    Font(R.font.markazitext_regular)
)

val displayTitle = TextStyle(
    fontFamily = markaziText,
    fontSize = 64.sp,
    fontWeight = FontWeight.Medium
)
val subTitle = TextStyle(
    fontFamily = markaziText,
    fontSize = 40.sp,
    fontWeight = FontWeight.Normal
)
//Text(
//text = "Hello World",
//style = displayTitle
//)

val karlaText = FontFamily(
    Font(R.font.karla_regular)
)
val sectionTitle = TextStyle(
    fontFamily = karlaText,
    fontSize = 20.sp,
    fontWeight = FontWeight.ExtraBold
)
val navBar = TextStyle(
    fontFamily = karlaText,
    fontSize = 20.sp,
    fontWeight = FontWeight.Bold
)
val leadText = TextStyle(
    fontFamily = karlaText,
    fontSize = 18.sp,
    fontWeight = FontWeight.Medium
)
val sectionCategories = TextStyle(
    fontFamily = karlaText,
    fontSize = 16.sp,
    fontWeight = FontWeight.ExtraBold
)
val cardTitle = TextStyle(
    fontFamily = karlaText,
    fontSize = 18.sp,
    fontWeight = FontWeight.Bold
)
val paragraphText = TextStyle(
    fontFamily = karlaText,
    fontSize = 16.sp,
    fontWeight = FontWeight.Normal
)
val highlightText = TextStyle(
    fontFamily = karlaText,
    fontSize = 16.sp,
    fontWeight = FontWeight.Medium
)