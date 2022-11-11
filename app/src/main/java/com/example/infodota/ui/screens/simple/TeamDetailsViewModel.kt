package com.example.infodota.ui.screens.simple

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.infodota.MainActivity
import com.example.infodota.ui.teams.TeamDetailsViewModel
import dagger.hilt.android.EntryPointAccessors

@Composable
fun teamDetailsViewModel(teamId: Int): TeamDetailsViewModel {
    val factory = EntryPointAccessors.fromActivity(
        LocalContext.current as Activity,
        MainActivity.ViewModelFactoryProvider::class.java
    ).teamDetailsViewModelFactory()

    return viewModel(factory = TeamDetailsViewModel.provideFactory(factory, teamId = teamId))
}
