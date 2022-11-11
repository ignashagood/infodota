package com.example.infodota.ui.heroes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.infodota.data.network.HeroesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HeroesViewModel @Inject constructor(private val repository: HeroesRepository) : ViewModel() {

    private var _state = MutableStateFlow<HeroesScreenState>(HeroesScreenState.Loading)
    val state: StateFlow<HeroesScreenState> by ::_state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllHeroes().collect {
                if (it.isSuccessful) {
                    _state.value = HeroesScreenState.Content(it.body() ?: emptyList())
                } else {
                    Timber.e(it.errorBody().toString())
                }
            }
        }
    }
}
