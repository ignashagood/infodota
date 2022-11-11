package com.example.infodota.ui.screens.simple

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.example.infodota.ui.theme.Orange700
import com.example.infodota.util.cinzelFontFamily

@Composable
fun PopupWindow(state: MutableState<Boolean>, attributeSort: MutableState<String>) {
    val context = LocalContext.current
    if (state.value) {
        Popup(
            alignment = Alignment.TopEnd,
            properties = PopupProperties(),
            offset = IntOffset(
                0,
                LocalDensity.current.run { 53.dp.toPx().toInt() }
            )
        ) {

            Box(
                Modifier
                    .size(220.dp, 120.dp)
                    .background(Orange700, RoundedCornerShape(bottomStart = 16.dp))
                    .border(2.dp, color = Color.Black, RoundedCornerShape(bottomStart = 16.dp))
            ) {
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.Start
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                attributeSort.value = "agi"
                                state.value = !state.value
                                Toast
                                    .makeText(
                                        context,
                                        "Heroes filtered of agility",
                                        Toast.LENGTH_LONG
                                    )
                                    .show()
                            }
                    ) {
                        Text(
                            text = "Agility",
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 12.sp,
                                fontFamily = cinzelFontFamily,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                    Dimen()
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                attributeSort.value = "str"
                                state.value = !state.value
                                Toast
                                    .makeText(
                                        context,
                                        "Heroes filtered of straight",
                                        Toast.LENGTH_LONG
                                    )
                                    .show()
                            }
                    ) {
                        Text(
                            text = "Straight",
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 12.sp,
                                fontFamily = cinzelFontFamily,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                    Dimen()
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                attributeSort.value = "int"
                                state.value = !state.value
                                Toast
                                    .makeText(
                                        context,
                                        "Heroes filtered of intelligence",
                                        Toast.LENGTH_LONG
                                    )
                                    .show()
                            }
                    ) {
                        Text(
                            text = "Intelligence",
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 12.sp,
                                fontFamily = cinzelFontFamily,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }
            }
        }
    }
}
