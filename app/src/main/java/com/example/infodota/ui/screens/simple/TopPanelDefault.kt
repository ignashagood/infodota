package com.example.infodota.ui.screens.simple

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.infodota.ui.theme.Orange700
import com.example.infodota.util.cinzelFontFamily

@Composable
fun TopPanelDefault(navController: NavController, title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Orange700),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { navController.popBackStack() },
            Modifier.weight(1f)
        ) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Back arrow")
        }
        VertDimen()
        Box(
            modifier = Modifier
                .weight(8f)
                .padding(start = 20.dp)
        ) {
            Text(
                text = title,
                style = TextStyle(
                    fontFamily = cinzelFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            )
        }
        VertDimen()
        IconButton(
            onClick = { },
            Modifier.weight(1f)
        ) {
            Icon(Icons.Filled.MoreVert, contentDescription = "Menu")
        }
    }
}
