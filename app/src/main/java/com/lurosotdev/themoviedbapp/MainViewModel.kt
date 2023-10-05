package com.lurosotdev.themoviedbapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel() : ViewModel() {

    //para reactividad en ui, hay dos componentes livedata(valido para kotlin y java)
    //y stateFlow para corutinas, mas flexibles pero mas complicados
//    var state by mutableStateOf(UiState())
//        private set
/*LiveData
*
* */
    private val _state = MutableLiveData(UiState())
    val state : LiveData<UiState> = _state
    init {
        viewModelScope.launch {
            _state.value = UiState(
                Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org/3/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(MoviesService::class.java)
                    .getMovies()
                    .results
            )
        }
    }

    data class UiState(
        val movies: List<ServerMovie> = emptyList()
    )


}