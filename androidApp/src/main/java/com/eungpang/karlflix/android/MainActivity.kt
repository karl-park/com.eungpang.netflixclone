package com.eungpang.karlflix.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eungpang.karlflix.Greeting
import com.eungpang.karlflix.domain.model.Movie
import com.eungpang.karlflix.domain.repository.MovieRepository
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

suspend fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : ComponentActivity() {
    private val movieRepository: MovieRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val coroutineScope = rememberCoroutineScope()
            lateinit var greetingText: String
            LaunchedEffect(Unit) {
                greetingText = greet()
            }

            var movieList by remember {
                mutableStateOf<List<Movie>>(emptyList())
            }

            Column(modifier = Modifier.fillMaxSize()) {
                Text(text = greetingText)

                TextButton(onClick = {
                    coroutineScope.launch {
                        val result = movieRepository.searchMovie("home")
                        if (result.isSuccess) {
                            movieList = movieList + result.getOrDefault(emptyList())
                        }
                    }

                }) {
                    Text("Click")
                }

                Spacer(modifier = Modifier.height(20.dp))

                LazyColumn {
                    items(movieList.size) {
                        val movie = movieList[it]
                        Text("title: ${movie.title}")
                    }
                }
            }
        }
    }
}