package com.example.infodota.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.infodota.ui.heroes.HeroesScreenState
import com.example.infodota.ui.heroes.HeroesViewModel
import com.example.infodota.ui.screens.simple.Dimen
import com.example.infodota.ui.screens.simple.TopPanelDefault

@Composable
fun HeroDetailsScreen(navController: NavController, viewModel: HeroesViewModel, itemId: String) {
    val viewState = viewModel.state.collectAsState()
    when (val state = viewState.value) {
        is HeroesScreenState.Content -> {
            val item = state.heroes
                .firstOrNull { it.id == itemId.toInt() }
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                item?.let {
                    val propertiesList = with(it) {
                        listOf(
                            "Base health" to base_health.toFloat(),
                            "Base mana" to base_mana.toFloat(),
                            "Base health regen" to base_health_regen,
                            "Base mana regen" to base_mana_regen,
                            "Base agility" to base_agi.toFloat(),
                            "Base straight" to base_str.toFloat(),
                            "Base intelligence" to base_int.toFloat(),
                            "Agility gain" to agi_gain,
                            "Straight agility" to str_gain,
                            "Move speed" to move_speed.toFloat(),
                        )
                    }
                    Dimen()
                    TopPanelDefault(navController = navController, title = item.localized_name)
                    Dimen()
                    LazyColumn(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        item {
                            AsyncImage(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight(0.3f),
                                model = "https://api.opendota.com${it.image}",
                                contentDescription = it.name,
                                contentScale = ContentScale.Crop
                            )
                        }

                        items(propertiesList) { property ->
                            PropertyItem(property)
                        }
                    }
                }
            }
        }
        is HeroesScreenState.Loading -> Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
        }
    }
}

@Composable
fun PropertyItem(property: Pair<String, Float>) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(horizontal = 10.dp, vertical = 20.dp),
        elevation = 6.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Text(text = property.first, style = TextStyle(fontSize = 16.sp))
            }
            Box(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Text(text = property.second.toString(), style = TextStyle(fontSize = 16.sp))
            }
        }
    }
}
