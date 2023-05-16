package com.john_halaka.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun MenuItem() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.white))
            .padding(top = 8.dp, bottom = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 8.dp, end = 16.dp),

            ) {
            Text(
                text = "Greek Salad",
                modifier = Modifier
                    .fillMaxWidth(),
                color = colorResource(id = R.color.black),
                style = cardTitle,
            )
        }


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(.7f)
                    .padding(end = 8.dp)
                    .height(IntrinsicSize.Min)


            ) {
                Text(
                    text = "The famous Greek salad of crispy lettuce, peppers, olives and our chic..",
                    style = paragraphText,
                    color = Color.DarkGray
                )
                Text(
                    text = "$12.99",
                    style = cardTitle,
                    color = colorResource(id = R.color.black)
                )
            }
            Image(
                painter = painterResource(id = R.drawable.greek_salad),
                contentDescription = "food",
                modifier = Modifier
                    .height(IntrinsicSize.Min)

            )
        }


    }


}
