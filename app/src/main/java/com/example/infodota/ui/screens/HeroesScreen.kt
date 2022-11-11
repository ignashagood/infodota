package com.example.infodota.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.infodota.R
import com.example.infodota.data.models.HeroNetworkModel
import com.example.infodota.navigation.Screen
import com.example.infodota.ui.heroes.HeroesScreenState
import com.example.infodota.ui.heroes.HeroesViewModel
import com.example.infodota.ui.screens.simple.Dimen
import com.example.infodota.ui.screens.simple.PopupWindow
import com.example.infodota.ui.screens.simple.SortInfoPanel
import com.example.infodota.ui.screens.simple.TopPanelList
import com.example.infodota.ui.screens.simple.TopPanelTeams
import com.example.infodota.ui.theme.Orange700
import com.example.infodota.util.cinzelFontFamily
import java.util.Locale

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HeroesScreen(
    navController: NavController,
    viewModel: HeroesViewModel,
) {
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    val popupState = remember { mutableStateOf(false) }
    val attributeSort = remember { mutableStateOf("") }
    Column {
        Dimen()
        TopPanelList(navController = navController, textFieldState = textState, popupState = popupState)
        Dimen()
        if (attributeSort.value != "") {
            SortInfoPanel(attribute = attributeSort)
        }
        HeroesList(
            navController = navController,
            viewModel = viewModel,
            textFieldState = textState,
            attributeSort
        )
        PopupWindow(state = popupState, attributeSort = attributeSort)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HeroesList(
    navController: NavController,
    viewModel: HeroesViewModel,
    textFieldState: MutableState<TextFieldValue>,
    attribute: MutableState<String>,
) {
    val viewState = viewModel.state.collectAsState()
    when (val listState = viewState.value) {
        is HeroesScreenState.Content -> {
            var filteredHeroes: List<HeroNetworkModel>
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.LightGray),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LazyVerticalGrid(
                    cells = GridCells.Fixed(3),
                    modifier = Modifier
                        .fillMaxSize(),
                    contentPadding = PaddingValues(4.dp)
                ) {
                    val searchedText = textFieldState.value.text
                    filteredHeroes = if (searchedText.isEmpty()) {
                        if (attribute.value == "") {
                            listState.heroes
                        } else {
                            listState.heroes.filter { it.primary_attr == attribute.value }
                        }
                    } else {
                        val defaultList =
                            if (attribute.value == "") {
                                listState.heroes
                            } else {
                                listState.heroes.filter { it.primary_attr == attribute.value }
                            }
                        val resultList = ArrayList<HeroNetworkModel>()
                        for (hero in defaultList) {
                            if (hero.localized_name.lowercase(Locale.getDefault())
                                .contains(searchedText.lowercase(Locale.getDefault()))
                            ) {
                                resultList.add(hero)
                            }
                        }
                        resultList
                    }
                    items(filteredHeroes) { hero ->
                        HeroListItem(hero = hero, navController)
                    }
                }
            }
        }
        is HeroesScreenState.Loading -> {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Orange700)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Loading...",
                        style = TextStyle(fontSize = 24.sp),
                        fontFamily = cinzelFontFamily,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun HeroListItem(hero: HeroNetworkModel, navController: NavController) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data("https://api.opendota.com${hero.image}")
            .crossfade(true)
            .build(),
        contentDescription = hero.localized_name,
        placeholder = painterResource(R.drawable.hero_place_holder),
        modifier = Modifier
            .size(128.dp, 80.dp)
            .padding(4.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable {
                navController.navigate(Screen.HeroDetails.route + "/${hero.id}")
            },
        contentScale = ContentScale.FillBounds
    )
}
