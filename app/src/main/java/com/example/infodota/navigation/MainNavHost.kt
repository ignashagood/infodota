package com.example.infodota.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.infodota.ui.heroes.HeroesViewModel
import com.example.infodota.ui.screens.HeroDetailsScreen
import com.example.infodota.ui.screens.HeroesScreen
import com.example.infodota.ui.screens.MainScreen
import com.example.infodota.ui.screens.TeamDetailScreen
import com.example.infodota.ui.screens.TeamsScreen
import com.example.infodota.ui.screens.simple.teamDetailsViewModel
import com.example.infodota.ui.teams.TeamsViewModel

const val MAIN_SCREEN = "main_screen"
const val HEROES_SCREEN = "heroes_screen"
const val HERO_DETAILS_SCREEN = "heroes_details_screen"
const val TEAMS_SCREEN = "teams_screen"
const val TEAMS_DETAILS_SCREEN = "teams_details_screen"

sealed class Screen(val route: String) {
    object Main : Screen(route = MAIN_SCREEN)
    object Heroes : Screen(route = HEROES_SCREEN)
    object HeroDetails : Screen(route = HERO_DETAILS_SCREEN)
    object Teams : Screen(route = TEAMS_SCREEN)
    object TeamDetails : Screen(route = TEAMS_DETAILS_SCREEN)
}

@Composable
fun MainNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Main.route
    ) {
        composable(route = Screen.Main.route) {
            MainScreen(navController = navController)
        }
        composable(route = Screen.Heroes.route) {
            val viewModel = hiltViewModel<HeroesViewModel>()
            HeroesScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = Screen.HeroDetails.route + "/{Id}") { backStackEntry ->
            backStackEntry.arguments?.getString("Id")?.let {
                val viewModel = hiltViewModel<HeroesViewModel>()
                HeroDetailsScreen(
                    navController = navController,
                    viewModel = viewModel,
                    itemId = it
                )
            }
        }
        composable(route = Screen.Teams.route) {
            val viewModel = hiltViewModel<TeamsViewModel>()
            TeamsScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = Screen.TeamDetails.route + "/{Id}" + "/{Name}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("Id")
            val name = backStackEntry.arguments?.getString("Name")
            id?.let {
                name?.let {
                    val viewModel = teamDetailsViewModel(teamId = id.toInt())
                    TeamDetailScreen(
                        viewModel = viewModel,
                        navController = navController,
                        teamName = name
                    )
                }
            }
        }
    }
}
