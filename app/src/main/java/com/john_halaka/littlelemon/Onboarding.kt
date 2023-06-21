package com.john_halaka.littlelemon


import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.preference.PreferenceManager
import kotlinx.coroutines.launch


@Composable
fun OnBoarding(navController: NavHostController) {
    val context = LocalContext.current
    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    val isRegistered = sharedPreferences.getBoolean("isRegistered", false)

    LaunchedEffect(isRegistered) {
        if (isRegistered) {
            navController.navigate(Home.route)

        }
    }
    if (!isRegistered) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = colorResource(id = R.color.primary1)),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Let's get to Know you",
                    Modifier
                        .align(Alignment.CenterVertically)

                        .padding(16.dp),
                    style = subTitle,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Personal information",
                Modifier
                    .align(Alignment.Start)
                    .padding(start = 16.dp),
                style = cardTitle
            )

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "First Name",
                Modifier
                    .align(Alignment.Start)
                    .padding(start = 16.dp, bottom = 8.dp),
                style = highlightText
            )

            var firstName by remember { mutableStateOf("") }
            TextField(
                value = firstName,
                onValueChange = { firstName = it },

                placeholder = { Text("Enter your name") },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Black,
                    backgroundColor = Color.White,
                    focusedIndicatorColor = Color.Black,
                    unfocusedIndicatorColor = Color.Gray
                ),

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 32.dp)
                    .border(width = 2.dp, color = Color.Gray, shape = RoundedCornerShape(16))
            )

            Text(
                text = "Last Name",
                Modifier
                    .align(Alignment.Start)
                    .padding(start = 16.dp, bottom = 8.dp),
                style = highlightText
            )

            var lastName by remember { mutableStateOf("") }
            TextField(
                value = lastName,
                onValueChange = { lastName = it },

                placeholder = { Text("Enter your last name") },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Black,
                    backgroundColor = Color.White,
                    focusedIndicatorColor = Color.Black,
                    unfocusedIndicatorColor = Color.Gray
                ),

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 32.dp)
                    .border(width = 2.dp, color = Color.Gray, shape = RoundedCornerShape(16))
            )

            Text(
                text = "Email",
                Modifier
                    .align(Alignment.Start)
                    .padding(start = 16.dp, bottom = 8.dp),
                style = highlightText
            )
            var email by remember { mutableStateOf("") }
            TextField(
                value = email,
                onValueChange = { email = it },

                placeholder = { Text("Enter your email") },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Black,
                    backgroundColor = Color.White,
                    focusedIndicatorColor = Color.Black,
                    unfocusedIndicatorColor = Color.Gray
                ),

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 32.dp)
                    .border(width = 2.dp, color = Color.Gray, shape = RoundedCornerShape(16))
            )

            Spacer(modifier = Modifier.weight(1f))

            val lifecycleOwner = LocalLifecycleOwner.current
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 32.dp),
                shape = RoundedCornerShape(16),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(id = R.color.primary2),
                    contentColor = Color.Black
                ),
                onClick = {

                    if (firstName.isBlank() || lastName.isBlank() || email.isBlank()) {
                        Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT)
                            .show()
                    } else {

                        lifecycleOwner.lifecycleScope.launch {
                            // save to shared prefs
                            val editor = sharedPreferences.edit()
                            editor.putString("firstName", firstName)
                            editor.putString("lastName", lastName)
                            editor.putString("email", email)
                            editor.putBoolean("isRegistered", true)
                            editor.apply()

                            navController.navigate(Home.route)
                            navController.popBackStack()

                        }
                    }
                }
            ) {
                Text(text = "Register")
            }
        }
    }
}








