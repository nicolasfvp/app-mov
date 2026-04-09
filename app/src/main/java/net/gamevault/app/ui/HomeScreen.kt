package net.gamevault.app.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.gamevault.app.model.GameUiState
import net.gamevault.app.viewmodel.GameViewModel

@Composable
fun HomeScreen(
    viewModel: GameViewModel,
    onNavigateToLibrary: () -> Unit
) {
    val uiState: GameUiState by viewModel.uiState.collectAsState()
    val games = uiState.games
    val total = games.count()
    var completedCount = 0
    var inProgressCount = 0

    games.forEach {
        if (it.progress == 100) completedCount++ else inProgressCount++
    }
    val completedPercentage = if (total > 0) (completedCount.toFloat() / total) * 100 else 0f
    val inProgressPercentage = if (total > 0) (inProgressCount.toFloat() / total) * 100 else 0f
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            Icons.Default.SportsEsports, 
            contentDescription = null, 
            modifier = Modifier.size(100.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(Modifier.height(16.dp))
        Text(
            "Bem-vindo ao GameVault", 
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Card(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Text(
                "Organize sua coleção e acompanhe seu progresso.", 
                Modifier.padding(16.dp),
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        Button(
            onClick = onNavigateToLibrary,
            modifier = Modifier.padding(bottom = 24.dp)
        ) {
            Text("Ver Minha Biblioteca")
        }
        
        Text(
            "Jogos Adicionados: $total", 
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.secondary
        )
        Text(
            "Jogos Completos: ${"%.0f".format(completedPercentage)}%", 
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            "Jogos Em Progresso: ${"%.0f".format(inProgressPercentage)}%", 
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}