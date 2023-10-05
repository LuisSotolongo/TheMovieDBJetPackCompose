package com.lurosotdev.themoviedbapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.lurosotdev.themoviedbapp.ui.theme.TheMovieDBAPPTheme
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
 //MVVM -Model View ViewModel
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TheMovieDBAPPTheme {
                  val viewModel: MainViewModel by viewModels()
               //hacer una conversion entre tipos de estado, el problema que el livedata no esta pensado para nulos, le tenemos que dar un valor por defecto a observerAsState
                val state by viewModel.state.observeAsState(MainViewModel.UiState())
                /*
                * conversion para los estados
                * val state = viewmodel.state.observerAsState(MainViewModel.UiState())
                * este seria la mejor opcion porque no repinta la ui
                *val state by viewModel.state.collectAsState()
                *
                * */

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        topBar = { TopAppBar(title = { Text(text = "Movies")}) }

                    ) { padding ->
                        LazyVerticalGrid(
                            columns = GridCells.Adaptive(120.dp),
                            modifier = Modifier.padding(padding),
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp),
                            contentPadding = PaddingValues(4.dp)
                        ) {
                            items(state.movies) { movie ->
                                Column {
                                    AsyncImage(
                                        model = "https://image.tmdb.org/t/p/w500${movie.poster_path}",
                                        contentDescription = movie.title,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .aspectRatio(2 / 3f)
                                    )
                                    Text(
                                        text = movie.title,
                                        modifier = Modifier.padding(16.dp),
                                        maxLines = 1
                                    )
                                }

                            }
                        }
                    }

                }

            }
        }
    }


}