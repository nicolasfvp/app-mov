package com.example.gamevault.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.gamevault.viewmodel.GameViewModel

@Composable
fun SummaryScreen(viewModel: GameViewModel, onBack: () -> Unit) {
    val uiState by viewModel.uiState.collectAsState()
    val favorites = uiState.games.filter { it.isFavorite }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Meus Favoritos", style = MaterialTheme.typography.h5)
        LazyColumn {
            items(favorites) { game ->
                ListItem(text = { Text(game.title) }, icon = { Icon(Icons.Default.Star, null, tint = Color.Yellow) })
            }
        }
        Button(onClick = onBack) { Text("Voltar") }
    }
}