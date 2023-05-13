package com.john_halaka.littlelemon

import android.graphics.drawable.PaintDrawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.preference.PreferenceManager

@Composable
fun ProfileScreen(navController: NavHostController) {
    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences (LocalContext.current)
    val firstName = rememberSaveable { mutableStateOf(sharedPreferences.getString("firstName", "") ?: "") }
    val lastName = rememberSaveable { mutableStateOf(sharedPreferences.getString("lastName", "") ?: "") }
    val email = rememberSaveable { mutableStateOf(sharedPreferences.getString("email", "") ?: "") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Little lemon logo",
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .padding(top = 16.dp, bottom = 16.dp)
                .size(36.dp)
        )

        Spacer(modifier = Modifier.height(64.dp))

        Text(
            text = "Personal information",
            Modifier
                .align(Alignment.Start)
                .padding(start = 16.dp),
            style = navBar
        )

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "First Name",
            Modifier
                .align(Alignment.Start)
                .padding(start = 16.dp, bottom = 8.dp, end = 16.dp),
            style = highlightText
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp,bottom = 8.dp)
                .border(
                width = 1.dp,
                color = Color.Gray,
                shape =
                RoundedCornerShape(4.dp)
            ),
            Alignment.BottomStart
        ) {
            Text(
                text = firstName.value,
                Modifier
                    .padding(start = 8.dp, end = 16.dp,top = 8.dp, bottom = 8.dp),
                style = highlightText
            )
        }


        Text(
            text = "Last Name",
            Modifier
                .align(Alignment.Start)
                .padding(start = 16.dp, bottom = 8.dp, end = 16.dp),
            style = highlightText
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp,bottom = 8.dp)
                .border(
                    width = 1.dp,
                    color = Color.Gray,
                    shape =
                    RoundedCornerShape(4.dp)
                ),
            Alignment.BottomStart
        ) {
            Text(
                text = lastName.value,
                Modifier
                    .padding(start = 8.dp, end = 16.dp,top = 8.dp, bottom = 8.dp),
                style = highlightText
            )
        }


        Text(
            text = "Email",
            Modifier
                .align(Alignment.Start)
                .padding(start = 16.dp, bottom = 8.dp, end = 16.dp),
            style = highlightText
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp,bottom = 8.dp)
                .border(
                    width = 1.dp,
                    color = Color.Gray,
                    shape =
                    RoundedCornerShape(4.dp)
                ),
            Alignment.BottomStart
        ) {
            Text(
                text = email.value,
                Modifier
                    .padding(start = 8.dp, end = 16.dp, top = 8.dp, bottom = 8.dp),
                style = highlightText
            )
        }


        Spacer(modifier = Modifier.weight(1f))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 32.dp),
            shape = RoundedCornerShape(16),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.primary2),
                contentColor = Color.Black
            ), onClick = {
                val editor = sharedPreferences.edit()
                editor.clear()
                editor.apply()
                navController.navigate(OnBoarding.route)
            }
        ){
                Text(text = "Log out")
            }

    }


}