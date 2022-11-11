package com.example.infodota.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.infodota.R
import com.example.infodota.navigation.Screen
import com.example.infodota.ui.screens.simple.Dimen
import com.example.infodota.ui.theme.Orange200
import com.example.infodota.ui.theme.Orange700
import com.example.infodota.util.cinzelFontFamily

@Composable
fun MainScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Orange200),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Orange700),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .padding(horizontal = 6.dp)
                    .size(40.dp),
                painter = painterResource(id = R.drawable.main_screen_menu),
                contentDescription = "Main screen menu"
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Text(
                    text = "INFODOTA",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontFamily = cinzelFontFamily,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 4.sp
                    )
                )
            }
        }
        Dimen()
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 10.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.Black)
                .clickable {
                    navController.navigate(Screen.Heroes.route)
                },
            contentAlignment = Alignment.Center,
        ) {
            AsyncImage(
                model = "https://wallpaperaccess.com/full/2499055.jpg",
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = "HEROES",
                style = TextStyle(
                    fontSize = 48.sp,
                    color = Color.Black,
                    fontFamily = cinzelFontFamily,
                    fontWeight = FontWeight.Bold
                ),
                letterSpacing = 18.sp
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 20.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.Black)
                .clickable {
                    navController.navigate(Screen.Teams.route)
                },
            contentAlignment = Alignment.Center,
        ) {
            AsyncImage(
                model = "https://games.mail.ru/hotbox/content_files/news/2022/05/17/1e9479a518ac464f9408df56ca6c0172.jpg",
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = "TEAMS",
                style = TextStyle(
                    fontSize = 48.sp,
                    color = Color.White,
                    fontFamily = cinzelFontFamily,
                    fontWeight = FontWeight.Bold
                ),
                letterSpacing = 18.sp
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .size(width = 165.dp, height = 220.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.Black)
            )
            Box(
                modifier = Modifier
                    .size(width = 165.dp, height = 220.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.Black)
            )
        }
    }
}
