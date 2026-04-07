package net.gamevault.app.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import net.gamevault.app.viewmodel.GameViewModel

@Composable
fun SummaryScreen(viewModel: GameViewModel, onBack: () -> Unit) {
    val uiState by viewModel.uiState.collectAsState()
    val favorites = uiState.games.filter { it.isFavorite }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Meus Favoritos", style = MaterialTheme.typography.headlineMedium)
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(favorites) { game ->
                ListItem(
                    headlineContent = { Text(game.title) },
                    leadingContent = { Icon(Icons.Default.Star, contentDescription = null, tint = Color.Yellow) }
                )
            }
        }
        Button(onClick = onBack) { Text("Voltar") }
    }
}