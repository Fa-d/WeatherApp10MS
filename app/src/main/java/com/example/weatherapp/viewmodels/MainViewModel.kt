package com.example.weatherapp.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.core.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository, val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val isLocationServiceOn = MutableStateFlow(false)

    fun getCurrentWeather(lat: Double, lon: Double) {
        viewModelScope.launch {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    mainRepository.getCurrentWeather(lat, lon)
                } catch (_: Exception) {

                }
            }
        }
    }

}