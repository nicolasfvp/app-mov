package net.gamevault.app.viewmodel

import net.gamevault.app.model.Achievement
import net.gamevault.app.model.Game
import net.gamevault.app.model.GameUiState
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    init {
        // Mock
        _uiState.update { it.copy(games = listOf(
            Game(
                id = 1,
                title = "Elden Ring",
                description = "Explore as vastas terras de...",
                imageUrl = "https://via.placeholder.com/150",
                rating = 5,
                progress = 80,
                isFavorite = true,
                achievements = listOf(
                    Achievement(1, "Elden Lord", true),
                    Achievement(2, "Shardbearer", true),
                    Achievement(3, "Age of Stars", false),
                    Achievement(4, "Lord of Frenzied Flame", false)
                )
            ),
            Game(
                id = 2,
                title = "Hades",
                description = "Fuja do inferno para...",
                imageUrl = "https://via.placeholder.com/150",
                rating = 5,
                progress = 100,
                achievements = listOf(
                    Achievement(1, "Escaped Underworld", true),
                    Achievement(2, "Family Reunion", true),
                    Achievement(3, "Master of Arms", true)
                )
            ),
            Game(
                id = 3,
                title = "Cyberpunk 2077",
                description = "Na futuristica cidade de NightCity..",
                imageUrl = "https://via.placeholder.com/150",
                rating = 4,
                progress = 40,
                achievements = listOf(
                    Achievement(1, "The Fool", true),
                    Achievement(2, "Breathtaking", false),
                    Achievement(3, "Never Fade Away", false),
                    Achievement(4, "The Sun", false),
                    Achievement(5, "Temperance", false)
                )
            )
        )) }
    }

    fun onSearchQueryChange(newQuery: String) {
        _uiState.update { it.copy(searchQuery = newQuery) }
    }

    fun toggleFavorite(gameId: Int) {
        _uiState.update { state ->
            val updatedGames = state.games.map {
                if (it.id == gameId) it.copy(isFavorite = !it.isFavorite) else it
            }
            state.copy(games = updatedGames)
        }
    }

    fun updateProgress(gameId: Int, newProgress: Int) {
        _uiState.update { state ->
            val updatedGames = state.games.map {
                if (it.id == gameId) it.copy(progress = newProgress) else it
            }
            state.copy(games = updatedGames)
        }
    }

    fun updateGame(gameId: Int, title: String, description: String, rating: Int, progress: Int) {
        _uiState.update { state ->
            val updatedGames = state.games.map {
                if (it.id == gameId) it.copy(
                    title = title,
                    description = description,
                    rating = rating.coerceIn(0, 5),
                    progress = progress.coerceIn(0, 100)
                ) else it
            }
            state.copy(games = updatedGames)
        }
    }

    fun toggleAchievement(gameId: Int, achievementId: Int) {
        _uiState.update { state ->
            val updatedGames = state.games.map { game ->
                if (game.id == gameId) {
                    val updatedAchievements = game.achievements.map { achievement ->
                        if (achievement.id == achievementId) achievement.copy(completed = !achievement.completed)
                        else achievement
                    }
                    game.copy(achievements = updatedAchievements)
                } else game
            }
            state.copy(games = updatedGames)
        }
    }
}