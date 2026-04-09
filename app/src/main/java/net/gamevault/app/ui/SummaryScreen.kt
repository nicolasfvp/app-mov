package net.gamevault.app.ui

import androidx.compose.foundation.background
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
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.gamevault.app.viewmodel.GameViewModel

@Composable
fun SummaryScreen(viewModel: GameViewModel, onBack: () -> Unit) {
    val uiState by viewModel.uiState.collectAsState()
    val favorites = uiState.games.filter { it.isFavorite }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
        .padding(16.dp)) {
        Text(
            "Meus Favoritos", 
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )
        LazyColumn(modifier = Modifier.weight(1f).padding(vertical = 16.dp)) {
            items(favorites) { game ->
                ListItem(
                    headlineContent = { 
                        Text(game.title, color = MaterialTheme.colorScheme.onSurface) 
                    },
                    supportingContent = { StarRating(rating = game.rating) },
                    leadingContent = { 
                        Icon(
                            Icons.Default.Star, 
                            contentDescription = null, 
                            tint = MaterialTheme.colorScheme.secondary 
                        ) 
                    },
                    colors = ListItemDefaults.colors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                )
            }
        }
        Button(
            onClick = onBack,
            modifier = Modifier.padding(top = 16.dp)
        ) { 
            Text("Voltar") 
        }
    }
}