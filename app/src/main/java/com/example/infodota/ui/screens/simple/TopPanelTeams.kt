package com.example.infodota.ui.screens.simple

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.infodota.ui.theme.Orange700
import com.example.infodota.util.cinzelFontFamily

@Composable
fun TopPanelTeams(
    navController: NavController,
    textFieldState: MutableState<TextFieldValue>,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Orange700),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { navController.popBackStack() },
            Modifier
                .weight(1f)
        ) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Back arrow")
        }
        VertDimen()
        TextField(
            value = textFieldState.value,
            onValueChange = { value ->
                textFieldState.value = value
            },
            modifier = Modifier
                .weight(9f)
                .height(50.dp),
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 16.sp,
                fontFamily = cinzelFontFamily,
                fontWeight = FontWeight.Bold
            ),
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = "Back arrow")
            },
            trailingIcon = {
                if (textFieldState.value != TextFieldValue("")) {
                    IconButton(
                        onClick = {
                            textFieldState.value =
                                TextFieldValue("") // Remove text from TextField when you press the 'X' icon
                        }
                    ) {
                        Icon(
                            Icons.Default.Close,
                            contentDescription = "",
                            modifier = Modifier
                                .size(24.dp)
                        )
                    }
                }
            },
            placeholder = {
                Text(
                    "Search team",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontFamily = cinzelFontFamily,
                        fontWeight = FontWeight.Bold
                    )
                )
            },
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black,
                backgroundColor = Orange700,
                cursorColor = Color.Black,
                leadingIconColor = Color.Black,
                trailingIconColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )
    }
}
