package net.gamevault.app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// SUBSTITUA PELOS CAMINHOS REAIS DO SEU PROJETO SE NECESSÁRIO
import net.gamevault.app.model.GameUiState
import net.gamevault.app.viewmodel.GameViewModel

@Composable
fun DetailScreen(
    gameId: Int?,
    viewModel: GameViewModel,
    onBack: () -> Unit
) {
    // R6: Estado imutável coletado do ViewModel
    val uiState: GameUiState by viewModel.uiState.collectAsState()

    val game = uiState.games.find { currentGame -> currentGame.id == gameId }

    if (game != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp) // R3: Uso de modificadores
        ) {
            IconButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
            }

            // R2: Componentes de texto com estilos do Material 3
            Text(text = game.title, style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = game.description, style = MaterialTheme.typography.bodyLarge)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Avaliação", style = MaterialTheme.typography.labelMedium)
                    Text(text = "${game.rating} ★", style = MaterialTheme.typography.titleLarge)
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Progresso", style = MaterialTheme.typography.labelMedium)
                    Text(text = "${game.progress}%", style = MaterialTheme.typography.titleLarge)
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // R7: Interação real que altera o estado do aplicativo
            Button(
                onClick = { viewModel.toggleFavorite(game.id) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = if (game.isFavorite) "Remover dos Favoritos" else "Adicionar aos Favoritos")
            }
        }
    } else {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Jogo não encontrado.")
        }
    }
}