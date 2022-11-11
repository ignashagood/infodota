package com.example.infodota.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.infodota.data.models.ProPlayerNetworkModel
import com.example.infodota.ui.screens.simple.Dimen
import com.example.infodota.ui.screens.simple.TopPanelDefault
import com.example.infodota.ui.teams.TeamDetailsState
import com.example.infodota.ui.teams.TeamDetailsViewModel
import com.example.infodota.util.cinzelFontFamily

@Composable
fun TeamDetailScreen(
    viewModel: TeamDetailsViewModel,
    navController: NavController,
    teamName: String,
) {
    val viewState = viewModel.state.collectAsState()
    when (val state = viewState.value) {
        is TeamDetailsState.Content -> {
            Column(modifier = Modifier.fillMaxSize()) {
                Dimen()
                TopPanelDefault(navController = navController, title = teamName)
                Dimen()
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(state.players) { player ->
                        PlayerCard(player = player)
                    }
                }
            }
        }
        is TeamDetailsState.Loading -> {
        }
    }
}

@Composable
fun PlayerCard(player: ProPlayerNetworkModel) {
    Card(
        modifier = Modifier
            .size(160.dp, 200.dp)
            .clip(RoundedCornerShape(16.dp)),
        elevation = 10.dp,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = player.avatarFull,
                contentDescription = player.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(100.dp)
                    .weight(3f),
            )
            Box(
                modifier = Modifier
                    .background(Color.Black)
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = player.name,
                    style = TextStyle(
                        fontFamily = cinzelFontFamily,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                )
            }
        }
    }
}
