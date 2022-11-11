package com.example.infodota.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.infodota.data.models.TeamNetworkModel
import com.example.infodota.navigation.Screen
import com.example.infodota.ui.screens.simple.Dimen
import com.example.infodota.ui.screens.simple.TopPanelTeams
import com.example.infodota.ui.teams.TeamsScreenState
import com.example.infodota.ui.teams.TeamsViewModel
import com.example.infodota.ui.theme.Orange700
import com.example.infodota.util.cinzelFontFamily
import java.util.Locale

@Composable
fun TeamsScreen(
    navController: NavController,
    viewModel: TeamsViewModel,
) {
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    Column {
        Dimen()
        TopPanelTeams(navController = navController, textFieldState = textState)
        Dimen()
        TeamsList(navController = navController, viewModel = viewModel, textState = textState)
    }
}

@Composable
fun TeamsList(
    navController: NavController,
    viewModel: TeamsViewModel,
    textState: MutableState<TextFieldValue>,
) {
    val viewState = viewModel.state.collectAsState()
    when (val listState = viewState.value) {
        is TeamsScreenState.Content -> {
            var filteredTeams: List<TeamNetworkModel>
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Gray),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentPadding = PaddingValues(4.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    val searchedText = textState.value.text
                    filteredTeams = if (searchedText.isEmpty()) {
                        listState.teams
                    } else {
                        val resultList = ArrayList<TeamNetworkModel>()
                        for (team in listState.teams) {
                            if (team.name.lowercase(Locale.getDefault())
                                .contains(searchedText.lowercase(Locale.getDefault()))
                            ) {
                                resultList.add(team)
                            }
                        }
                        resultList
                    }
                    items(filteredTeams) { team ->
                        TeamListItem(team = team, navController = navController)
                    }
                }
            }
        }
        is TeamsScreenState.Loading -> {
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
fun TeamListItem(team: TeamNetworkModel, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable {
                navController.navigate(Screen.TeamDetails.route + "/${team.teamId}" + "/${team.name}")
            },
        elevation = 10.dp,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(Color.Black),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    modifier = Modifier
                        .size(70.dp)
                        .padding(4.dp),
                    model = team.logo,
                    contentDescription = team.name,
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = team.name,
                    style = TextStyle(
                        fontFamily = cinzelFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                )
            }
        }
    }
}
