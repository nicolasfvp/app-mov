package net.gamevault.app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import net.gamevault.app.viewmodel.GameViewModel

@Composable
fun DetailScreen(
    gameId: Int?,
    viewModel: GameViewModel,
    onBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val game = uiState.games.find { it.id == gameId }

    var isEditing by remember { mutableStateOf(false) }

    if (game == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Jogo não encontrado.")
        }
        return
    }

    var editTitle by remember(game.title) { mutableStateOf(game.title) }
    var editDescription by remember(game.description) { mutableStateOf(game.description) }
    var editRating by remember(game.rating) { mutableIntStateOf(game.rating) }
    var editProgress by remember(game.progress) { mutableStateOf(game.progress.toString()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Top bar: back button + edit/save button
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
            }
            if (isEditing) {
                Button(onClick = {
                    val progressValue = editProgress.toIntOrNull()?.coerceIn(0, 100) ?: game.progress
                    viewModel.updateGame(game.id, editTitle, editDescription, editRating, progressValue)
                    isEditing = false
                }) {
                    Text("Salvar")
                }
            } else {
                IconButton(onClick = { isEditing = true }) {
                    Icon(Icons.Filled.Edit, contentDescription = "Editar")
                }
            }
        }

        // Game title
        if (isEditing) {
            OutlinedTextField(
                value = editTitle,
                onValueChange = { editTitle = it },
                label = { Text("Nome") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
        } else {
            Text(
                text = game.title,
                style = MaterialTheme.typography.headlineMedium
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Game icon
        Icon(
            imageVector = Icons.Default.SportsEsports,
            contentDescription = "Game icon",
            modifier = Modifier
                .size(80.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Star rating
        if (isEditing) {
            Text("Avaliação", style = MaterialTheme.typography.labelMedium)
            StarRating(
                rating = editRating,
                starSize = 32.dp,
                onRatingChange = { editRating = it }
            )
        } else {
            Text("Avaliação", style = MaterialTheme.typography.labelMedium)
            StarRating(rating = game.rating, starSize = 32.dp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Progress bar
        Text("Progresso", style = MaterialTheme.typography.labelMedium)
        Spacer(modifier = Modifier.height(4.dp))

        if (isEditing) {
            OutlinedTextField(
                value = editProgress,
                onValueChange = { newVal ->
                    if (newVal.isEmpty() || newVal.toIntOrNull() != null) {
                        editProgress = newVal
                    }
                },
                label = { Text("Progresso (0-100)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.DarkGray)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(fraction = game.progress / 100f)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFF4CAF50))
                )
                Text(
                    text = "${game.progress}%",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Description
        if (isEditing) {
            OutlinedTextField(
                value = editDescription,
                onValueChange = { editDescription = it },
                label = { Text("Descrição") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 2,
                maxLines = 4
            )
        } else {
            Text(text = game.description, style = MaterialTheme.typography.bodyLarge)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Achievements section
        Text("Conquistas", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(game.achievements) { achievement ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = achievement.name,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.weight(1f)
                        )
                        if (isEditing) {
                            Checkbox(
                                checked = achievement.completed,
                                onCheckedChange = {
                                    viewModel.toggleAchievement(game.id, achievement.id)
                                }
                            )
                        } else {
                            Icon(
                                imageVector = if (achievement.completed) Icons.Filled.CheckCircle else Icons.Outlined.Circle,
                                contentDescription = if (achievement.completed) "Completa" else "Incompleta",
                                tint = if (achievement.completed) Color(0xFF4CAF50) else Color.Gray
                            )
                        }
                    }
                }
            }
        }

        // Favorite button at bottom
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { viewModel.toggleFavorite(game.id) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = if (game.isFavorite) "Remover dos Favoritos" else "Adicionar aos Favoritos")
        }
    }
}
